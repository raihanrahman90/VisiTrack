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
    data = db.child('pelanggaran').get()
    print(type(data))
    return jsonify({'jumlah_orang': str(getJumlahOrang()),
                    'jumlah_kamera': 1,
                    'jumlah_pelanggaran':len(data.val()),
                    'started': True})

@app.route("/pelanggaran")
def getPelanggaran():
    pelanggaran = []
    data = db.child('pelanggaran').get()
    for item in data.each():
        pelanggaran.append({"id":item.key(), "pesan":item.val()['pelanggaran'], "gambar":item.val()['gambar'], "kamera":item.val()['kamera'], "tanggal":item.val()["tanggal"], "status":item.val()["status"]})
    return jsonify({"success":"true", "pelanggaran":pelanggaran})

@app.route("/pelanggaran/<id>", methods=["POST","GET"])
def getPelanggaranId(id):
    if request.method == 'GET':
        data = db.child('pelanggaran').get()
        print(id)
        for item in data.each():
            if item.key() == id:
                print(item.key())
                return jsonify({"pesan":item.val()['pelanggaran'], "gambar":item.val()['gambar'], "kamera":item.val()['kamera'], "tanggal":item.val()["tanggal"], "status":item.val()["status"]})
        return jsonify({"success":False})
    if request.method == 'POST':
        db.child("pelanggaran").child(id).update({"status":request.json["status"]})
        return jsonify({"success":True})
 
@app.route("/gambar/<namafile>")
def gambar(namafile):
    return send_file(namafile, mimetype='image/gif')

@app.route("/logout", methods=['POST'])
def logout():
    token = request.json['token']
    token = jwt.decode(token, key, algorithms=["HS256"])
    username = token["username"]
    password = token["password"]
    data = db.child("users").get()
    for item in data.each():
        data = item.val()
        if(data['username']==username and data['password']==password):
            success = "true"
            db.child("users").child(item.key()).update({"token":""})
            return jsonify({"success":success})
    return jsonify({"success":False})