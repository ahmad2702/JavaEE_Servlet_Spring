#! /bin/sh

echo "--- REQUESTING ALL JSON CONTACT ------------"
curl -X GET \
     -v "http://localhost:8080/helloJAXRS/rest/contacts"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING ALL XML CONTACT--------"
curl -X GET \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/helloJAXRS/rest/contacts"
echo " "
echo "-------------------------------------------------------------------------------------------------"
