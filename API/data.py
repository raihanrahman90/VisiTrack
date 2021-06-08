import pyrebase
from pyfcm import FCMNotification
push_service = FCMNotification(api_key="AAAA4DzgdXQ:APA91bHcXwGidspqNUWJfVY9FH9c9gxpam7_7wK4zePqESX9OMrv4ZvkPQ5r9wA9StES2MQUj_Ggm-d2NTDbcU7HvF9ZZN2OCZFuWppuk8YlRwABFXENDJitq1G8cwR6JCevf1ZPkWzU")
token = []
config = {
    "apiKey": "AIzaSyCg7Y1AltObqbFDYcbyyKoiwqIjW-HyPDM",
    "authDomain": "isitrack-6f31e.firebaseapp.com",
    "databaseURL": "https://visitrack-6f31e-default-rtdb.firebaseio.com/",
    "storageBucket": "visitrack-6f31e.appspot.com"
}
firebase = pyrebase.initialize_app(config)
auth = firebase.auth()
user = auth.sign_in_with_email_and_password("c1161482@bangkit.academy", "nabilakbar")
user = auth.sign_in_anonymous()
db = firebase.database()
def getConnection():
    return db

#get all login token device from firebase
def getToken():
    global token
    token = []
    dataUser = db.child("users").get()
    for item in dataUser.each():
        try:
            if(item.val()["token"]==""):
                continue
            token.append(item.val()["token"])
        except:
            continue

#send notification to all login device
def kirimNotif(judul, body):
    global token
    result = push_service.notify_multiple_devices(registration_ids=token, message_title=judul, message_body=body)
    print(result)
getToken()
