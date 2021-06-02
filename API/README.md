## Installing the Library

1. Open the Anaconda Prompt and install the dependencies for windows,

```
pip install flask
pip install threading
pip install pyrebase4
pip install pyjwt
pip install lxml
pip install jupyter
pip install tensorflow-object-detection-api
```

2. Look at the tasks.py, Look for load_model() function, set model_dir to the location of Train object detection
3. Change the mask_model direction to the trained mask model
4. set PATH_TO_LABELS to the location of list class that you got from training object detection model
5. Go to data.py, set push_service api_key to your firebase api key
6. set config at data.py to configuration that you got from firebase cloud messaging
7. look at line 13 at data.py, change email and password to your cloud messaging login
8. enable login with email and password at your firebase
9. open cmd at API folder, run `set FLASK_APP=index` and then `flask run`
10. And your API run at 127.0.0.1:5000

## Models used in Tensorflow Object Detection API

![Models used in Tensorflow Object Detection API](https://cdn-images-1.medium.com/max/800/1*-EyxSs2OiyWm-E6MSpSJiA.png)
