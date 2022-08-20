import 'package:flutter_local_notifications/flutter_local_notifications.dart';

LocalNotification localNotification=LocalNotification();

class LocalNotification {
  FlutterLocalNotificationsPlugin flutterLocalNotificationsPlugin=FlutterLocalNotificationsPlugin();

  initLocalNotificationPlugin()async{

    const AndroidInitializationSettings initializationSettingsAndroid=
    AndroidInitializationSettings('mipmap/ic_launcher');
    final IOSInitializationSettings initializationSettingsIOS=IOSInitializationSettings(
        requestAlertPermission: false, requestBadgePermission: false, requestSoundPermission: false
    );
    final MacOSInitializationSettings initializationSettingsMacOS=MacOSInitializationSettings(
        requestAlertPermission: false, requestBadgePermission: false, requestSoundPermission: false
    );
    final InitializationSettings initializationSettings=InitializationSettings(
        android: initializationSettingsAndroid, iOS: initializationSettingsIOS ,macOS: initializationSettingsMacOS
    );
    await flutterLocalNotificationsPlugin.initialize(initializationSettings);


  }
  void requestPermission (){ //ios용

    flutterLocalNotificationsPlugin.resolvePlatformSpecificImplementation<
        IOSFlutterLocalNotificationsPlugin>()
        ?.requestPermissions(
      alert: true,
      badge: true,
      sound: true,
    );

  }


  Future<void> sampleNotification() async{
    const AndroidNotificationDetails androidPlatformChannelSpecifics=
    AndroidNotificationDetails('channelId', 'channelName',
        channelDescription: 'channel description',
        importance:  Importance.max,
        priority: Priority.max,
        showWhen: false
    );

    const NotificationDetails platformChannelSpecifics =NotificationDetails(
        android: androidPlatformChannelSpecifics,
        iOS: IOSNotificationDetails()
    );

    await flutterLocalNotificationsPlugin.show(
        0, '알림', '새로운 리스트가 업데이터 되었습니다', platformChannelSpecifics,
        payload: 'item x'
    );

  }
}
