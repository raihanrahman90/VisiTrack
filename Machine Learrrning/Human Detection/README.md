
## Credits & Links

1. [Download Tensorflow Object Detection API](https://github.com/tensorflow/models)
2. [How to install Tensorflow Object Detection](https://github.com/tensorflow/models/blob/master/research/object_detection/g3doc/installation.md)

# Train model

1. open Train object detection.ipynb
# Use model
This tutorial discusses how to configure the Tensorflow Object Detection API in windows and how implement custom object detection.

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
