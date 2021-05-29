import os
import time
from flask import Flask, jsonify
from threading import Thread
from tasks import runProgram, getJumlahOrang

app = Flask(__name__)
app.secret_key = os.urandom(42)

thread = Thread(target=runProgram, args=())
thread.daemon = True
thread.start()

@app.route("/")
def index():
    return jsonify({'thread_name': str(getJumlahOrang()),
                    'started': True})

