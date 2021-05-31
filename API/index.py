import os
import time
from flask import Flask, jsonify, request
from threading import Thread
import pyrebase
import jwt
key = "secret"
config = {
  "apiKey": "AIzaSyCg7Y1AltObqbFDYcbyyKoiwqIjW-HyPDM",
  "authDomain": "isitrack-6f31e.firebaseapp.com",
  "databaseURL": "https://visitrack-6f31e-default-rtdb.firebaseio.com/",
  "storageBucket": "visitrack-6f31e.appspot.com"
}
firebase = pyrebase.initialize_app(config)
# Get a reference to the auth service
auth = firebase.auth()

# Log the user in
user = auth.sign_in_with_email_and_password("m1161483@bangkit.academy", "raihan")

# Log the user in anonymously
user = auth.sign_in_anonymous()

# Get a reference to the database service
db = firebase.database()

app = Flask(__name__)
app.secret_key = os.urandom(42)
@app.route("/login", methods=['POST', 'GET'])
def index():
    username = request.json['username']
    password = request.json['password']
    token = request.json['token']
    success = "false"
    data = db.child("users").get()
    for item in data.each():
        data = item.val()
        if(data['username']==username and data['password']==password):
            success = "true"
            db.child("users").child(item.key()).update({"token":token})
            encoded = jwt.encode({"username": username, "password":password}, key, algorithm="HS256")
            return jsonify({"success":success, "token":encoded})
    return jsonify({"success":"false"})


@app.route("/register", methods=['POST'])
def daftar():
    username = request.json['username']
    password = request.json['password']
    data = {"username":username, "password":password}
    db.child('users').push(data)
    return jsonify({"success":"true"})