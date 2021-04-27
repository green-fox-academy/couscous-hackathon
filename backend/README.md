###### GET  /item/id
- returns
    - id
    - title
    - price
    - description
    - image_list
    - amount
    - category

###### GET /item
- returns a list
    - id
    - title
    - price
    - image
    - category
  
###### POST /cart
- needs
    - cart_id
    - item_id
    - user_amount

###### GET /cart
- needs 
    - cart_id
- returns a list
    - id
    - title
    - price
    - image
    - amount
    - user_amount
    - final_price

###### PUT /cart
- needs
    - cart_id
    - item_id
    - user_amount
- returns
    - item_price
    - final_price

###### DELETE /cart
- needs
    - cart_id
    - item_id
- returns a list
    - id
    - title
    - price
    - image
    - amount
    - user_amount
    - final_price

###### POST /payment
- needs
    - cart_id
