###### GET  /item/id
- needs: id as path variable
- returns for example:
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
    - item_id
    - item_amount

###### GET /cart
- returns an object: final_price and a list of items
    - final_price (total, sum of total item prices)
    - item_list
        - id
        - title
        - price
        - image
        - amount
        - user_amount
        - final_price

###### PUT /cart
- needs
    - item_id
    - item_amount
- returns
    - item_price
    - final_price

###### DELETE /cart
- needs
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
