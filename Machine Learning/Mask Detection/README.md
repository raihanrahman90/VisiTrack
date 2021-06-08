# Face Mask Detection
This project use a Deep Neural Network, more specifically a Convolutional Neural Network, to differentiate between images of people with and without masks. The CNN manages to get an accuracy of **96.01% on the training set** and **91.18% on the validation set**. The CNN was created using the Google Collaboratory infrastructure with the Python programming language and using the Keras Framework and TensorFlow then saved in Jupyter Notebooks ".ipynb" file.
## Dataset Overview
The dataset from Kaggle (https://www.kaggle.com/prithwirajmitra/covid-face-mask-detection-dataset). This dataset contains about 1006 images with 503 images containing images of people wearing masks and 503 images with people without masks, for mask and nonmask. You can download the datasets and upload it on your google drive or local storage.
## Build and Train Model on _Google Colaboratory_
1. Click on "Facemask Detection VisiTrack.ipynb"
2. Open the file using _Google Colab_
3. Enable the GPUs for the notebook 
4. Click on "Runtime" then choose "Run All" or press Ctrl+F9 on keyboard
5. Wait the process until finished (about 5-10 minutes) to train the model
6. Or you can run it manually on each cell or row to see how each process works
7. After the training is done, download the model (Mask_detection.h5)
8. The model can be deploy as API to be use on another platform to make a real-time facemask detection using camera or video  
## Detection Test
1. Click on "Mask Detection.ipnynb"
2. Open the file using _Google Colab_
3. Run every cell
4. On the last cell, upload image of people using mask or without mask to test the model
5. After the process done, it will show a "(image_name.jpg) **is using mask**" or "(image_name.jpg) **is not using mask**" depends on the people in picture using mask or not 
