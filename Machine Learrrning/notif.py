token = "fkLljg4WR6mGg5XwjwQLsZ:APA91bFLelEctsy2jG-az3AtAElA7nwx1eZ45z2-pPCASu58VMQEa50cgs0JhBcCKtzKWnqE_nf7z3UJhpKfWhl3WTmb0RIt9x185cU6Ixg98rkM-tiNJLvn4Zz1AICwDGOlUbwgu_h4"
# Send to single device.
from pyfcm import FCMNotification

push_service = FCMNotification(api_key="AAAA4DzgdXQ:APA91bHcXwGidspqNUWJfVY9FH9c9gxpam7_7wK4zePqESX9OMrv4ZvkPQ5r9wA9StES2MQUj_Ggm-d2NTDbcU7HvF9ZZN2OCZFuWppuk8YlRwABFXENDJitq1G8cwR6JCevf1ZPkWzU")

# OR initialize with proxies


# Your api-key can be gotten from:  https://console.firebase.google.com/project/<project-name>/settings/cloudmessaging

registration_id = token
message_title = "Uber update"
message_body = "Hi john, your customized news for today is ready"
result = push_service.notify_single_device(registration_id=registration_id, message_title=message_title, message_body=message_body)

# Send to multiple devices by passing a list of ids.
registration_ids = [token]
message_title = "Uber update"
message_body = "Hope you're having fun this weekend, don't forget to check today's news"
result = push_service.notify_multiple_devices(registration_ids=registration_ids, message_title=message_title, message_body=message_body)

print(result)