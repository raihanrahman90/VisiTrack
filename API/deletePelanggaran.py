from data import getConnection

db = getConnection()
kamera = []
data = db.child("kamera").get()
for item in data.each():
    print(item)
    print(item.val())
    kamera.append({"kamera":item.val()['kamera']})