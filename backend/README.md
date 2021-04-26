GET /item/id
- returns one item (with id 1 for test purposes)
    - id;
    - title;
    - price;
    - description;
    - image_list;
    - amount;
    - category;

GET /item
- returns list of items (three of them for test purposes)
    - id;
    - title;
    - price;
    - (first )image;
    - category;



POST /cart
- needs:
    - cart_id
    - item_id
    - user_amount

GET /cart
- needs: cart_id
- returns a list:
    - id;
    - title;
    - price;
    - (first) image;
    - amount;
    - user_amount;
    - final_price;

PUT /cart
- needs
    - cart_id
    - item_id
    - user_amount
- returns
    - item_price
    - final_price

/DELETE
- needs
    - cart_id
    - item_id
- returns a list:
    - id;
    - title;
    - price;
    - (first) image;
    - amount;
    - user_amount ;
    - final_price;

POST /payment
- needs
    - cart_id
