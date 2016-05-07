#!/usr/bin/python

import sys
import os
import socket
import MySQLdb
import datetime
import random

def Register(register_list):
	
	name=register_list[1]
	user_email=register_list[2]
	password=register_list[3]
	category=register_list[4]
	try:
        	cursor.execute("""INSERT INTO user(user_email, name, password, category) Values(%s,%s,%s,%s)""", (str(user_email),str(name),str(password),str(category)))
        	db.commit()
		return "Done"
	except Exception as error:
		return "Error"

def Login(login_list):
	
        user_email=login_list[1]
        password=login_list[2]
 
	cmd = "select password from user where user_email=" + "'" + user_email + "'"
	cursor.execute(cmd)
	db.commit()
	status = cursor.fetchall()

      	for value in status:
       		database_password = value[0]

	if database_password == 0:
		return "Wrong Password"	
	else:
		if database_password == password:

			return "Verified"
		else:
		
			return "Wrong Password"

def Broadcast(broadcast_list):
	
	session_email = broadcast_list[1]
	event_name = broadcast_list[2]
	event_description = broadcast_list[3]
	event_date = broadcast_list[4]
	event_time = broadcast_list[5]
	event_location = broadcast_list[6]
	#print event_date
		
	month,day,year = event_date.split('/')
	hour,minutes = event_time.split(':')
	second = "00"
	event_date = datetime.datetime(int(year),int(month),int(day))
	
	event_time = datetime.datetime(int(year),int(month),int(day),int(hour),int(minutes),int(second))
	print event_date
	print event_time

	try:
        	cmd = "select id from user where user_email=" + "'" + session_email + "'"
        	cursor.execute(cmd)
        	db.commit()
       	 	status = cursor.fetchall()
        	for value in status:
                	user_id = value[0]
	except Exception as error:
		print error
		return "Failed"

	try:
       		cursor.execute("""INSERT INTO event_detail(event_name,event_description,event_date,event_time,event_location,user_id) Values(%s,%s,%s,%s,%s,%s)""", (str(event_name),str(event_description),str(event_date),str(event_time),str(event_location),str(user_id)))
       		db.commit()
	except Exception as error:
		print error
		return "Failed"

	try:
        	cmd = "select id from event_detail where event_name=" + "'" + event_name + "'"
        	cursor.execute(cmd)
        	db.commit()
		status = cursor.fetchall()
		for value in status:
			event_id = value[0]	
	
		cmd = "INSERT into event_verification(event_id) Values( " + str(event_id) + ")"
		cursor.execute(cmd)
		db.commit()
	except Exception as error:
		print error
		return "Failed"

	return "Submitted"

def ViewEvent():
	
	try:
		list_event = []
		list_event_database = []
		current_time = datetime.datetime.now()
		print current_time
    		cursor.execute("""SELECT event_name from event_detail,event_verification where event_detail.id = event_verification.event_id and event_verification.event_status="Confirmed" and event_time > %s """, (current_time,))
    		db.commit()
		list_of_event = cursor.fetchall()

		for element in list_of_event:
			list_event_database.append(element)


	       	if len(list_event_database) == 0:
                	return "Failed"

		else:
			level1 = map(list, list_event_database)
			for element in level1:
				for element1 in element:
					list_event.append(element1)			
			return (list_event)
	except Exception as error:
		print error
		return "Failed"

def AdminVerifyEvent(received_list):
	#email = received_list[1]	
	#print email
	try:
                list_event = []
                list_event_database = []
              	cmd = "select event_name from event_detail as e, event_verification as v where event_status='pending' and e.id = v.event_id" 
                cursor.execute(cmd)
                db.commit()
			
		list_of_event = cursor.fetchall()

		for element in list_of_event:
                        list_event_database.append(element)

                if len(list_event_database) == 0:
                        return "Failed"

                else:
                        level1 = map(list, list_event_database)
		
                        for element in level1:
                                for element1 in element:
                                        list_event.append(element1)
			
                        return (list_event)
        except Exception as error:
                print error
                return "Failed"

def ViewDetailEvent(receivedlist):
	list_event = []
	event_name = receivedlist[1]
	event_name = event_name.strip()
	try:
                list_event = []
                list_event_database = []
                current_time = datetime.datetime.now()

                cursor.execute("""SELECT event_description,event_time,event_location from event_detail where event_name = %s""", (event_name,))
                db.commit()
                list_of_event = cursor.fetchall()

		for row in list_of_event:
			event_description,event_time,event_location = row
	
		print event_description
		print event_time
		print event_location
		
		event_time = str(event_time)          

		date,time = event_time.split(" ")


		list_event.append(event_name)
		list_event.append(event_description)
		list_event.append(date)
		list_event.append(time)
		list_event.append(event_location)
            	return (list_event)
        except Exception as error:
                print "This is the error:" + str(error)
                return "Failed"

def AdminDetailEvent(received_list):
        list_event = []
        event_name = receivedlist[1]
        event_name = event_name.strip()
        try:
                list_event = []
                list_event_database = []
                current_time = datetime.datetime.now()

                cursor.execute("""SELECT event_description,event_time,event_location from event_detail where event_name = %s""", (event_name,))
                db.commit()
                list_of_event = cursor.fetchall()

                for row in list_of_event:
                        event_description,event_time,event_location = row

                print event_description
                print event_time
                print event_location

                event_time = str(event_time)

                date,time = event_time.split(" ")

                list_event.append(event_name)
                list_event.append(event_description)
                list_event.append(date)
                list_event.append(time)
                list_event.append(event_location)
                return (list_event)
        except Exception as error:
                print "This is the error:" + str(error)
                return "Failed"


def EventVerify(received_list):
	event_name = received_list[1]
	event_name = event_name.strip()
	event_name1 = "'" + event_name + "'"	
	#print event_name1

	try:
		cmd = "update event_detail,event_verification set event_status='Confirmed' where event_detail.event_name = " + event_name1
		cursor.execute(cmd)
		db.commit()

	except Exception as error:
		print error
		return "Failed"


def EventDecline(received_list):
        event_name = received_list[1]
        event_name = event_name.strip()
        event_name1 = "'" + event_name + "'"   
        try:
		cmd = "update event_detail,event_verification set event_status='Decline' where event_verification.event_id = " + event_name1
                cursor.execute(cmd)
                db.commit()

        except Exception as error:
		print error
                return "Failed"

def UserDelete(received_list):
        user_email = received_list[1]
        user_email = user_email.strip()
        user_email = "'" + user_email + "'"
	

        try:
		cmd = "update user set is_blocked=1 where user_email = " + user_email;
                cursor.execute(cmd)
                db.commit()

        except Exception as error:
		print error 
                return "Failed"

def ResetPassword(received_list):
	email = received_list[1]
	password = received_list[2]

	print email
	print password
	try:
		cmd = "update user set password = " + '"' + str(password) + '"' + " where user_email = " + "'" + str(email) + "'"
		print cmd
                cursor.execute(cmd)
                db.commit()

        except Exception as error:
		print error
                return "Failed"

def ModifyEvent(received_list):

        list_event = []
      	user_email = receivedlist[1]
        user_email = user_email.strip()
        try:
                list_event = []
                list_event_database = []
                current_time = datetime.datetime.now()

                cursor.execute("""SELECT event_description,event_time,event_location from event_detail where user_email = %s""", (user_email,))
                db.commit()
                list_of_event = cursor.fetchall()

                for row in list_of_event:
                        event_description,event_time,event_location = row

                print event_description
                print event_time
                print event_location

                event_time = str(event_time)

                date,time = event_time.split(" ")


                list_event.append(event_name)
                list_event.append(event_description)
                list_event.append(date)
                list_event.append(time)
                list_event.append(event_location)
                return (list_event)
        except Exception as error:
                print "This is the error:" + str(error)
                return "Failed"



def event_thread(sc,receive):

   	received_list = receive.split(',')
  	method=received_list[0]
	
	if method == "Register":
      		output = Register(received_list)
		if output == "Done":
			print sc.getpeername()
			sc.sendto("Done\n\n\n",sc.getpeername())
			sc.close()
		else:
			sc.sendto("Error\n",sc.getpeername())
			sc.close()

	elif method == "Login":
		output = Login(received_list)

		if output == "Verified":
                        print sc.getpeername()
		
                        sc.sendto("Verified\n\n\n",sc.getpeername())
                        sc.close()
			pass
		else:
                        sc.sendto("Wrong Password\n\n\n",sc.getpeername())
                        sc.close()

	elif method == "Broadcast":
		output = Broadcast(received_list)
		
                if output == "Submitted":
                        print sc.getpeername()

                        sc.sendto("Submit\n\n\n",sc.getpeername())
                        sc.close()
                        pass
                else:
                        sc.sendto("Failure\n\n\n",sc.getpeername())
                        sc.close()

	elif method == "ViewEvent":
		list_to_send = []
		output = ViewEvent()
		
		if output == "Failed":
			sc.sendto("Failure\n", sc.getpeername())
		else:
			sc.sendto(str(output) + "\n",sc.getpeername())
		#if output == "Submitted":
		#	pass

	elif method == "ViewDetailEvent":
		output = ViewDetailEvent(received_list)
                if output == "Failed":
                        sc.sendto("Failure\n", sc.getpeername())
                else:
                        sc.sendto(str(output) + "\n",sc.getpeername())


	elif method == "AdminVerifyEvent":
		list_to_send = []
                output = AdminVerifyEvent(received_list)

                if output == "Failed":
                        sc.sendto("Failure\n", sc.getpeername())
                else:
                        sc.sendto(str(output) + "\n",sc.getpeername())
                #if output == "Submitted":
                #       pass

	elif method == "AdminDetailEvent":
		output = ViewDetailEvent(received_list)
                if output == "Failed":
                        sc.sendto("Failure\n", sc.getpeername())
                else:
                        sc.sendto(str(output) + "\n",sc.getpeername())


	elif method == "EventVerify":
		output = EventVerify(received_list)
		if output == "Failed":
                        sc.sendto("Failure\n", sc.getpeername())
                else:
                        sc.sendto("Success\n",sc.getpeername())


        elif method == "EventDecline":
                output = EventDecline(received_list)
                if output == "Failed":
                        sc.sendto("Failure\n", sc.getpeername())
                else:
                        sc.sendto("Success\n",sc.getpeername())


	elif method == "AdminDeleteUser":
		output = UserDelete(received_list)
                if output == "Failed":
                        sc.sendto("Failure\n", sc.getpeername())
                else:
                        sc.sendto("Success\n",sc.getpeername())

	elif method == "ResetPassword":
		output = ResetPassword(received_list)
		if output == "Failed":
                        sc.sendto("Failure\n", sc.getpeername())
                else:
                        sc.sendto("Success\n",sc.getpeername())


        elif method == "ModifyEvent":
                output = ModifyEvent(received_list)
                if output == "Failed":
                        sc.sendto("Failure\n", sc.getpeername())
                else:
                        sc.sendto(str(output) + "\n",sc.getpeername())



 	else:
      		pass

def server_(HOST,PORT):
		s.setsockopt(socket.SOL_SOCKET,socket.SO_REUSEADDR,1)
		s.bind((HOST,PORT))
		s.listen(5)
		while True:	
			print "Listening at", s.getsockname()
			sc, sockname = s.accept()

			print "\n\nClient Request Received:"
			print "Accepted Connection from:", sockname
			print "Socket Connects", sc.getsockname(), 'and', sc.getpeername()		  
			receive = sc.recv(MAX_SIZE)
			print receive
			event_thread(sc,receive)
	


db = MySQLdb.connect(host='hackdfw.clovskkswzmt.us-west-2.rds.amazonaws.com',
                     user='ebay',
                     passwd='utaevent2016',
                     db='uta_event',
                     port=3306)
cursor = db.cursor()

	
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
HOST = '0.0.0.0'
PORT = 7000
MAX_SIZE = 65535
server_(HOST,PORT)
