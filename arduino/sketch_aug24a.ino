#include <ESP8266.h>
#include <ESP8266Client.h>

#include <Debug.h>
#include <JSN270.h>
#include <Arduino.h>
#include <SoftwareSerial.h>
#include <math.h>
 
#define SSID      "younghoon"    // your wifi network SSID
#define KEY       "87654321"    // your wifi network password
#define AUTH       "WPA2"     // your wifi network security (NONE, WEP, WPA, WPA2)

#define USE_DHCP_IP 1

#if !USE_DHCP_IP
#define MY_IP          "192.168.137.97"
#define SUBNET         "255.255.255.0"
#define GATEWAY        "192.168.137.1"
#endif

//#define HOST_IP        "112.175.184.92"
#define HOST_IP        "192.168.137.1"
#define REMOTE_PORT    80
#define PROTOCOL       "TCP"

SoftwareSerial mySerial(3, 2); // RX, TX
 
JSN270 JSN270(&mySerial);
char hostip[32];
char c;
String hostname;
void setup() {
  mySerial.begin(9600);
  Serial.begin(9600);

  Serial.println("--------- JSN270 Web Client Test --------");

  // wait for initilization of JSN270
  delay(5000);
  //JSN270.reset();
  delay(1000);

  //JSN270.prompt();
  JSN270.sendCommand("at+ver\r");
  delay(5);
  while(JSN270.receive((uint8_t *)&c, 1, 1000) > 0) {
    Serial.print((char)c);
  }
  delay(1000);

#if USE_DHCP_IP
  JSN270.dynamicIP();
#else
  JSN270.staticIP(MY_IP, SUBNET, GATEWAY);
#endif    

    
  if (JSN270.join(SSID, KEY, AUTH)) {
    Serial.println("WiFi connect to " SSID);
  }
  else {
    Serial.println("Failed WiFi connect to " SSID);
    Serial.println("Restart System");

    return;
  }
  delay(1000);

  JSN270.sendCommand("at+wstat\r");
  delay(5);
  while(JSN270.receive((uint8_t *)&c, 1, 1000) > 0) {
    Serial.print((char)c);
  }
  delay(1000);        

  JSN270.sendCommand("at+nstat\r");
  delay(5);
  while(JSN270.receive((uint8_t *)&c, 1, 1000) > 0) {
    Serial.print((char)c);
  }
  delay(1000);
  hostname = "192.168.137.1";
 
  Serial.print("Host IP is ");
  Serial.println(hostname);
  hostname.toCharArray(hostip, hostname.length()+1);  // convert string to char array

  if (!JSN270.client(hostip, REMOTE_PORT, PROTOCOL)) {  // 서버 연결 실패
    Serial.print("Failed connect to ");
    Serial.println(hostip);
    Serial.println("Restart System");
  } else {
    Serial.print("Socket connect to ");
    Serial.println(hostip);
    //delay(2000);
    
    // Enter data mode
    JSN270.sendCommand("at+exit\r");
    delay(100);

    Serial.println("connected to server"); 
    
//    JSN270.print("GET /android/push_notification.php?");
//    JSN270.println("temp=1");
//    JSN270.println(" HTTP/1.1");
//    JSN270.println("Host: 192.168.137.1");
//    JSN270.println("Connection: close");
//    JSN270.println();
  }
}
void startConnect()
{
  hostname = "192.168.137.1";
 
  Serial.print("Host IP is ");
  Serial.println(hostname);
  hostname.toCharArray(hostip, hostname.length()+1);  // convert string to char array

  if (!JSN270.client(hostip, REMOTE_PORT, PROTOCOL)) {  // 서버 연결 실패
    Serial.print("Failed connect to ");
    Serial.println(hostip);
    Serial.println("Restart System");
  } else {
    Serial.print("Socket connect to ");
    Serial.println(hostip);
    //delay(2000);
    
    // Enter data mode
    JSN270.sendCommand("at+exit\r");
    delay(100);

    Serial.println("connected to server");
  }
}
void loop() {
 Serial.println("loop start"); 
 delay(1000);
 if(JSN270.connected())
 {
    JSN270.print("GET /android/push_notification.php?");
    JSN270.println("temp=1");
    JSN270.println(" HTTP/1.1");
    JSN270.println("Host: 192.168.137.1");
    JSN270.println("Connection: close");
    JSN270.println();
    Serial.println("서버 접속 종료");
 }
 else
 {
  Serial.println("서버 접속 다시 시도");
  startConnect();
 }

}

