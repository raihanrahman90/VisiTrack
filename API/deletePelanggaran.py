from data import getConnection

db = getConnection()
data = db.child("pelanggaran").remove()