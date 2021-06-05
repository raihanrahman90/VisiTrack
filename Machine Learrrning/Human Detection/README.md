# Human Detection

this model trained use tensorflow-object-detection-api. This model will return detected object in an image and its location. With this model we could know the position of the object, and we will use it as part of social distancing violation detection.
## Credits & Links

1. [Download Tensorflow Object Detection API](https://github.com/tensorflow/models)
2. [How to install Tensorflow Object Detection](https://github.com/tensorflow/models/blob/master/research/object_detection/g3doc/installation.md)

# Train model

1. open Train object detection.ipynb
2. Upload your dataset to your drive at /TFJS/gambar_test and /TFJS/gambar_train, or you can change the path inside "Train object detection.ipynb"
3. First we should choose pretrained model from tensor flow, you can see all pretrainedmodel at (https://github.com/tensorflow/models) and put its name on MODEL variabel
4. Run all cell at "Train Object Detection.ipynb" and then you get the fine_tuned_models at '/content/object_detection_demo_flow/fine_tuned_model/' and '/content/object_detection_demo_flow/data/annotations/label_map.pbtxt' to get all classes name

# Use model
This tutorial discusses how to configure the Tensorflow Object Detection API in windows and how implement custom object detection.
1. open Test Object detection on video.ipynb
2. Upload your trained model and class list at github or drive
3. put it's path at model_dir, and put class list path at PATH_TO_LABELS
4. Choose your video by put it's path at video variable at last cell
5. and run it all

## Installing the Tensorflow Object Detection API

1. Download the tensorflow object detection api from [Github](https://github.com/tensorflow/models)
2. Open the Anaconda Prompt and install the dependencies for windows,

```
pip install tensorflow
pip install Cython
pip install contextlib2
pip install pillow
pip install lxml
pip install jupyter
```

3. Download the files from this repository
4. Copy and paste ```protoc.exe``` file in the path ```models-master\research```
5. Open the Commmand Prompt in ```models-master\research``` and copy and run the command included in ```protoc command.txt```
6. Copy the files ```Test Object detection on video``` into ```models-master\research```
7. Run above codes and check
