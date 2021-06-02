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
token= []
def getToken():
    return token
@app.route("/")
def percobaan():
    return "nabil"
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
            db.child("users").child(item.key()).update({"token":token})
            encoded = jwt.encode({"username": username, "password":password}, key, algorithm="HS256")
            getToken()
            return jsonify({"success":True, "token":encoded})
    return jsonify({"success":False})


@app.route("/register", methods=['POST'])
def daftar():
    username = request.json['username']
    password = request.json['password']
    data = {"username":username, "password":password}
    db.child('users').push(data)
    return jsonify({"success":True})

@app.route("/statistik")
def statistik():
    data = db.child('pelanggaran').get()
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
    return jsonify({"success":True, "pelanggaran":pelanggaran})

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
            db.child("users").child(item.key()).update({"token":""})
            getToken()
            return jsonify({"success":True})
    return jsonify({"success":False})

@app.route("/kamera")
def getKamera():
    kamera = []
    pelanggaran = db.child("pelanggaran").get()
    jumlah_pelanggaran = len(pelanggaran.val())
    data = db.child("kamera").get()
    for item in data.each():
        kamera.append({"kamera":item.val()['kamera'], "jumlah_orang":str(getJumlahOrang()), "gambar":item.val()["gambar"], "jumlah_pelanggaran":jumlah_pelanggaran})
    return jsonify({"success":True, "kamera":kamera})

    
if __name__ == '__main__':
    app.run(host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))
