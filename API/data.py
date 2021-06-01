import pyrebase
from pyfcm import FCMNotification
push_service = FCMNotification(api_key="AAAA4DzgdXQ:APA91bHcXwGidspqNUWJfVY9FH9c9gxpam7_7wK4zePqESX9OMrv4ZvkPQ5r9wA9StES2MQUj_Ggm-d2NTDbcU7HvF9ZZN2OCZFuWppuk8YlRwABFXENDJitq1G8cwR6JCevf1ZPkWzU")
def getConnection():
    config = {
    "apiKey": "AIzaSyCg7Y1AltObqbFDYcbyyKoiwqIjW-HyPDM",
    "authDomain": "isitrack-6f31e.firebaseapp.com",
    "databaseURL": "https://visitrack-6f31e-default-rtdb.firebaseio.com/",
    "storageBucket": "visitrack-6f31e.appspot.com"
    }
    firebase = pyrebase.initialize_app(config)
    auth = firebase.auth()
    user = auth.sign_in_with_email_and_password("m1161483@bangkit.academy", "raihan")
    user = auth.sign_in_anonymous()
    db = firebase.database()
    return db

def kirimNotif(judul, body):
    tokenRaihan = "e093oF6IR7GY6JWhIos02Q:APA91bEQ47ysaOlHHgMxyPxl2H3Ft_KmKnONVU2326xgXLvZh4bUR-XhQORJT2kX8FIDLvGyWuQfWStNvjZzEmjUvC2v4j5mCn0wwyRpdGPEr5j9zjXnmP8T9nfIRf2VgnDRRVSWZq8Q"
    registration_id = tokenRaihan
    result = push_service.notify_single_device(registration_id=registration_id, message_title=judul, message_body=body)
    print(result)