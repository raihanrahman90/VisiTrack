import os
import time
from flask import Flask, jsonify, request, send_file
from threading import Thread
from tasks import runProgram, getJumlahOrang
import pyrebase
import jwt
from data import getConnection
key = "secret"

app = Flask(__name__)
app.secret_key = os.urandom(42)

thread = Thread(target=runProgram, args=())
thread.daemon = True
thread.start()
db = getConnection()
@app.route("/")
def percobaan():
    return "A"
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

@app.route("/statistik")
def statistik():
    return jsonify({'jumlah_orang': str(getJumlahOrang()),
                    'started': True})

@app.route("/pelanggaran")
def getPelanggaran():
    pelanggaran = []
    data = db.child('pelanggaran').get()
    for item in data.each():
        pelanggaran.append({"id":item.key(), "pesan":item.val()['pelanggaran'], "gambar":item.val()['gambar'], "kamera":item.val()['kamera']})
    return jsonify({"success":"true", "pelanggaran":pelanggaran})

@app.route("/pelanggaran/<id>")
def getPelanggaranId(id):
    data = db.child('pelanggaran').get()
    for item in data.each():
        if item.key() == id:
            return jsonify({"pesan":item.val()['pelanggaran'], "gambar":item.val()['gambar']})

@app.route("/gambar/<namafile>")
def gambar(namafile):
    return send_file(namafile, mimetype='image/gif')