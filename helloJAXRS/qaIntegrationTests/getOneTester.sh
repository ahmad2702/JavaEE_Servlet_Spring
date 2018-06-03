#! /bin/sh

echo "--- REQUESTING CONTACT 2 IN XML--------"
curl -X GET \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/helloJAXRS/rest/contacts/2"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING CONTACT 1 IN JSON--------"
curl -X GET \
     -H "Accept: application/json" \
     -v "http://localhost:8080/helloJAXRS/rest/contacts/1"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING CONTACT 22 --------"
curl -X GET \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/helloJAXRS/rest/contacts/22"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING CONTACT with id NEW --------"
curl -X GET \
     -H "Accept: application/json" \
     -v "http://localhost:8080/helloJAXRS/rest/contacts/NEW"
echo " "
echo "-------------------------------------------------------------------------------------------------"