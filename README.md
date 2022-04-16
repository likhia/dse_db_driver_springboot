mvn clean compile

mvn spring-boot:run

# Add Product
curl --request POST \
    --url http://localhost:8080/db/add  \
    --header 'content-type: application/json' \
    --data '{"id":"","productname":"Heavy Lift Arms","description":"Heavy lift arms capable of lifting 1,250 lbs of weight per arm. Sold as a set.","price":"4199.99","created":""}'

curl --request POST \
    --url http://localhost:8080/db/add  \
    --header 'content-type: application/json' \
    --data '{"id":"","productname":"Product Name 1","description":"Product Description 1","price":"99.99","created":""}'


# Get Product By Id 
curl --request GET \
    --url http://localhost:8080/db/getProductById/b34d19cb-e284-4a58-bb76-b4fca89cf475


# Update Product
curl --request PUT \
    --url http://localhost:8080/db/update  \
    --header 'content-type: application/json' \
    --data '{"id":"de0c3474-0890-4ce8-ad4b-ce95ceddb504","productname":"testing update","description":"testing update","price":"","created":""}'

# Delete Product
curl --request DELETE \
    --url http://localhost:8080/db/delete/de0c3474-0890-4ce8-ad4b-ce95ceddb504





create table sample.product
(
    id UUID,
    productname text,
    description text, 
    price decimal, 
    created timestamp,
    primary key (id)
);
