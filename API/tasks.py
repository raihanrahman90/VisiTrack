import numpy as np
import os
import six.moves.urllib as urllib
import sys
import tarfile
import tensorflow as tf
import zipfile
import pathlib
from PIL import Image
from collections import defaultdict
from io import StringIO
from matplotlib import pyplot as plt
from PIL import Image
from IPython.display import display

from object_detection.utils import ops as utils_ops
from object_detection.utils import label_map_util
from object_detection.utils import visualization_utils as vis_util
from data import getConnection, kirimNotif
import cv2
from math import ceil

keras = tf.keras
utils_ops.tf = tf.compat.v1
tf.gfile = tf.io.gfile
db = getConnection()
model_mask = keras.models.load_model('../../Mask_detection.h5')
frame = 0
lastFrame =-30
PATH_TO_LABELS = './modelHuman.pbtxt'
category_index = label_map_util.create_category_index_from_labelmap(PATH_TO_LABELS, use_display_name=True)
category_index[42] = {"id":42, "name":"alert"}


def load_model():
  model_dir = r"C:\Users\Asus\.keras\datasets\modelHuman-master\saved_model"
  model = tf.saved_model.load(str(model_dir))
  model = model.signatures['serving_default']
  return model


def run_inference_for_single_image(model, image):
    image = np.asarray(image)
    input_tensor = tf.convert_to_tensor(image)
    input_tensor = input_tensor[tf.newaxis,...]
    output_dict = model(input_tensor)
    num_detections = int(output_dict.pop('num_detections'))
    output_dict = {key:value[0, :num_detections].numpy() 
                    for key,value in output_dict.items()}
    output_dict['num_detections'] = num_detections
    output_dict['detection_classes'] = output_dict['detection_classes'].astype(np.int64)
    if 'detection_masks' in output_dict:
    # Reframe the the bbox mask to the image size.
        detection_masks_reframed = utils_ops.reframe_box_masks_to_image_masks(
                output_dict['detection_masks'], output_dict['detection_boxes'],
                image.shape[0], image.shape[1])      
        detection_masks_reframed = tf.cast(detection_masks_reframed > 0.5,
                                        tf.uint8)
        output_dict['detection_masks_reframed'] = detection_masks_reframed.numpy()
    return output_dict


def maskPredict(x):
    x= tf.image.resize(x, [150, 150])
    x = np.expand_dims(x, axis=0)
    images = np.vstack([x])
    classes = model_mask.predict(images, batch_size=32)
    if classes[0]>0.5:
        return 0, images
    return 1, images



def pelanggaran(text, img):
    nama = time.time()
    im = Image.fromarray(img)
    im.save(str(nama)+".jpg")
    db.child("pelanggaran").push({"pelanggaran":text, "gambar":str(nama)+".jpg", "kamera":"20210530_163856", "tanggal":nama, "status":0})
    kirimNotif(text,text)



def show_inference(model, image_path):
    # the array based representation of the image will be used later in order to prepare the
    # result image with boxes and labels on it.
    global lastFrame
    global frame
    image_np = image_path
    image_np=cv2.cvtColor(image_np,cv2.COLOR_BGR2RGB)
    # Actual detection.
    output_dict = run_inference_for_single_image(model, image_np)
    # Visualization of the results of a detection.
    classDataset = output_dict['detection_classes']
    panjangData = len(output_dict['detection_classes'])
    listIndexOfDetected = [x for x in range(0, panjangData) if classDataset[x]==1 and output_dict["detection_scores"][x]>0.5]
    alert=[]
    tinggi = image_np.shape[0]
    lebar = image_np.shape[1]
    global jumlah_orang
    jumlah_orang = len(listIndexOfDetected)
    for i in listIndexOfDetected:
        potong = tf.image.crop_to_bounding_box(image_np, 
                                             ceil(output_dict['detection_boxes'][i][0]*tinggi), 
                                             ceil(output_dict['detection_boxes'][i][1]*lebar),
                                             ceil(((output_dict['detection_boxes'][i][2]*tinggi)-(output_dict['detection_boxes'][i][0]*tinggi))*0.2),
                                             ceil((output_dict['detection_boxes'][i][3]*lebar)-(output_dict['detection_boxes'][i][1]*lebar))
                                             )
        masker,foto = maskPredict(potong)
        if( masker == 0 and frame-lastFrame>30):
            lastFrame = frame
            pelanggaran("Tidak Menggunakan Masker", image_np)
        for j in listIndexOfDetected:
            if i==j:
                continue
            else:
                a = output_dict['detection_boxes'][i]
                b = output_dict['detection_boxes'][j]
                jarakX = (a[3]+a[1])/2-(b[3]+b[1])/2
                jarakY = a[2]-b[2]
                jarak = (jarakX**2+jarakY**2)**0.5
                if(jarak < (a[3]-a[1])*1.5):
                    alert.append(output_dict['detection_boxes'][i])
                    output_dict['detection_classes'][i]=42
                    break
    vis_util.visualize_boxes_and_labels_on_image_array(
      image_np,
      output_dict['detection_boxes'],
      output_dict['detection_classes'],
      output_dict['detection_scores'],
      category_index,
      instance_masks=output_dict.get('detection_masks_reframed', None),
      use_normalized_coordinates=True,
      line_thickness=2)
    image_np=cv2.cvtColor(image_np,cv2.COLOR_BGR2RGB)
    return image_np, alert


from datetime import datetime
import time
jumlah_orang = 0


def hitungJarak(data1,data2):
    x1 = (data1[3]+data1[1])/2
    x2 = (data2[3]+data2[1])/2
    y1 = data1[2]
    y2 = data2[2]
    return ((x1-x2)**2+(y1-y2)**2)**0.5

def getJumlahOrang():
    return jumlah_orang
    
def runProgram():
    id=0
    detection_model = load_model()
    video=cv2.VideoCapture(r'E:/TEST/20210530_163856.mp4')	##video input test
    DetectedBefore = np.array([])
    global frame
    while(True):
        try:
            frame +=1
            ret,img=video.read()
            img, detected=show_inference(detection_model,img)
            if(len(detected)>0):
                if len(DetectedBefore)==0:
                    for i in detected:
                        DetectedBefore = np.append(DetectedBefore, {"id":id,"lokasi":i,"detik":0, "tidak_terdetek":0})
                        id+=1
                else:
                    listDrop = []
                    for i in detected:
                        for j in range(len(DetectedBefore)):
                            data1=DetectedBefore[j]["lokasi"]
                            data2 =i
                            if hitungJarak(data1,data2) <0.1:
                                DetectedBefore[j]["lokasi"]=data2
                                DetectedBefore[j]["detik"]=DetectedBefore[j]["detik"]+1
                                if(DetectedBefore[j]["detik"]%5==0):
                                    pelanggaran("Kerumunan", img)
                            else:
                                DetectedBefore[j]["lokasi"]=data2
                                DetectedBefore[j]["tidak_terdetek"]=DetectedBefore[j]["tidak_terdetek"]+1
                                if(DetectedBefore[j]["tidak_terdetek"])>5:
                                    listDrop.append(j)
                    DetectedBefore = np.delete(DetectedBefore, listDrop)
        except:
            video=cv2.VideoCapture(r'E:/TEST/20210530_163856.mp4')	##video input test
