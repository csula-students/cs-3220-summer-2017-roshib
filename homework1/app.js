// single state store
class Store {
    constructor(storage) {
        this.storage = storage; // assuming local storage will be passed in as storage
        // these are the key name you can use in Store
        this.CART_KEY = 'CART';
        this.QUEUE_KEY = 'QUEUE';
        this.FOODS_KEY = 'FOODS';
    }

    // you can get item by store.cartItems
    get cartItems() {
        return JSON.parse(this.storage.getItem(this.CART_KEY));
    }

    // to call setter, simply "assign" like store.cartItems = something
    set cartItems(cartItems) {
        this.storage.setItem(this.CART_KEY, JSON.stringify(cartItems));
    }

    get queue() {
        return JSON.parse(this.storage.getItem(this.QUEUE_KEY));
    }

    set queue(queue) {
        this.storage.setItem(this.QUEUE_KEY, JSON.stringify(queue));
    }

    get foods() {
        return JSON.parse(this.storage.getItem(this.FOODS_KEY));
    }

    set foods(foods) {
        this.storage.setItem(this.FOODS_KEY, JSON.stringify(foods));
    }
}

class Cart {
    // take dom element into JavaScript class to attachment events
    constructor(root, store) {
        this.root = root;
        this.store = store;
        this.items = this.store.cartItems;
        this.init();
        this.PlacedOrder = false;
    }

    init() {
        // Render a list of items under root element
        this.render();
        // TODO: attach remove cart items to rendered HTML
    }

    destroy() {
        // TODO: remove all the events attached from init
        let deleteButtons = document.querySelectorAll('.delete_button');
        for (var i = 0; i < deleteButtons.length; i++) {
            let newButton = deleteButtons[i];
            newButton.removeEventListener('click', () => {
                let item = this.items[parseInt(newButton.dataset.index)];
                this.deleteItem(item);
            });
        }

        let deleteAllItems = document.querySelector('.delete_all_button');
        deleteAllItems.removeEventListener('click', () => {
            this.deleteAllItemsMethod();
        });

        let confirmOrderButton = document.querySelector('.confirm_order_button');
        confirmOrderButton.removeEventListener('click', () => {
            this.placeOrder();
        });
    }

    // remove an item from shopping cart
    deleteItem(item) {
        // TODO: logic to remove an item from cart
        if (this.items != null) {
            var updated_list = [];
            var to_compare = item[0];
            for (var i = 0; i < this.items.length; i++) {
                if (this.items[i][0] != to_compare) {
                    updated_list.push(this.items[i]);
                }
            }
            this.store.cartItems = updated_list;
            this.items = updated_list;
        }
        this.render();
    }

    deleteAllItemsMethod() {
        this.store.cartItems = [];
        this.items = [];
        this.render();
    }

    placeOrder() {
        // add item to statuses in store as status "in progress"
        if (this.items !== null) {
            var queueItems = [];
            if (this.store.queue !== null) {
                for (var i = 0; i < this.store.queue.length; i++) {
                    queueItems.push(this.store.queue[i]);
                }
            }
            for (var i = 0; i < this.items.length; i++) {
                queueItems.push([this.items[i][0], this.items[i][1], this.items[i][2], Number(this.items[i][3])]);
            }
            this.store.queue = queueItems;
            this.PlacedOrder = true;
            this.deleteAllItemsMethod();
        }
    }

    // render a list of item under root element
    render() {
        let tbody = this.root.querySelector('tbody');
        // using innerHTML to render a list of table row item under tbody
        tbody.innerHTML = ``;
        if (this.items === null) {
            this.items = [];
        } else if ((this.items.length == 0) && (this.PlacedOrder)) {
            tbody.innerHTML +=
                `<tr class="cart-table">
                <td colspan="4" class="cart-table">
                    <p >Successful!</p>
                </td>
            </tr>`;
            this.PlacedOrder = false;
            return;
        } else if (this.items.length == 0) {
            tbody.innerHTML +=
                `<tr class="cart-table">
                <td colspan="4" class="cart-table">
                    <p>Nothing in the table!!</p>
                </td>
            </tr>`;
            return;
        }
        for (var i = 0; i < this.items.length; i++) {
            // for each item in cartItems, create a row with the item name and image in one cell, and then a box of quantity in the other cell.
            var image_name = this.items[i][0];
            var item_name = this.items[i][1];
            var item_price = Number(this.items[i][2]);
            var item_quantity = Number(this.items[i][3]);

            tbody.innerHTML +=
                `<tr class="cart-table">
                    <td class="cart-table">
                    <img src=${image_name} class="indexTableImage">
                        <h4>${item_name}</h4>
                    </td>
                     <td class="cart-table">
                        <h4>$${item_price}</h4>
                    </td >
                    <td class="cart-table">
                        <h4>${item_quantity}</h4>
                        <button class="delete_button" data-name=${item_name} data-index=${i}>Remove</button>
                    </td>
                </tr>`;
        }

        /* tbody.innerHTML +=
             `<tr class="cart-table">
                 <td class="cart-table">
                     <button class="delete_all_button">Clear All From Inventory!</button>
                     <br><br>
                     <button class="confirm_order_button">Submit Inventory Request!</button>
                 </td>
             </tr>`;*/

        let removeButtons = document.querySelectorAll('.delete_button');
        for (var i = 0; i < removeButtons.length; i++) {
            let btn = removeButtons[i];
            btn.addEventListener('click', () => {
                let item = this.items[parseInt(btn.dataset.index)];
                this.deleteItem(item);
            });
        }

        let removeAllButton = document.querySelector('.delete_all_button');
        removeAllButton.addEventListener('click', () => {
            this.deleteAllItemsMethod();
        });

        let confirmOrderButton = document.querySelector('.confirm_order_button');
        confirmOrderButton.addEventListener('click', () => {
            this.placeOrder();
        });
    }
}

class CheckoutButton {
    constructor(root, store) {
        this.root = root;
        this.store = store;
        this.onClick = () => this.addItemToCart();
        this.init();
    }

    init() {
        this.root.addEventListener("click", this.onClick);
    }

    destroy() {
        this.root.removeEventListener("click", this.onClick);
    }

    addItemToCart() {
        // hint: you can use `dataset` to access data attributes
        // See passing data from HTML to JavaScript from course note
        let cartItems = this.store.cartItems || [];
        // TODO: replace with actual item
        var new_cart_item = true;
        for (var i = 0; i < cartItems.length; i++) {
            var existing_cart_item_name = cartItems[i][1]
            if (this.root.dataset.name === existing_cart_item_name) {
                var amount_to_add = Number(this.root.dataset.quantity);
                cartItems[i][3] += amount_to_add;
                new_cart_item = false;
            }
        }
        if (new_cart_item) {
            cartItems.push([this.root.dataset.image, this.root.dataset.name, Number(this.root.dataset.price), Number(this.root.dataset.quantity)]);
        }
        this.store.cartItems = cartItems;
    }
}

class StatusTable {
    // take dom element into JavaScript class to attachment events
    constructor(root, store) {
        this.root = root;
        this.store = store;
        this.init();
    }

    init() {
        // attach click event listener to table header row on each column
        this.render();
        // not doing sorting as of now
    }

    destroy() {
        // remove all the events attached from init
        let clearHistoryButton = document.querySelector('.clear_history_button');
        clearHistoryButton.removeEventListener('click', () => {
            this.clearHistory();
        });
    }

    sort(columnName) {
        // after sorting the array of statuses, re render item to update view
        this.render();
    }

    clearHistory() {
        this.store.queue = [];
        this.render();
    }

    // render rows of items under table using root.innerHTML
    render() {
        let tbody = this.root.querySelector('tbody');
        // using innerHTML to render a list of table row item under tbody
        tbody.innerHTML = ``;
        if (this.store.queue === null) {
            this.store.queue = [];
        }
        if (this.store.queue.length == 0) {
            tbody.innerHTML +=
                `<tr class="order_status_table">
                <td colspan="4" class="order_status_table" >
                    <p>You have purchased nothing! <br>
                    Click <a style="text-decoration: none;color:white;" href="menu.html" class="link">here</a>.</p>
                </td>
            </tr>`;
            return;

        }

        for (var i = 0; i < this.store.queue.length; i++) {
            // for each item in local storage's QUEUE, create a row with a cell for the item name and image, a cell for quantity, and a cell for status (In Progress for now).
            var image_name = this.store.queue[i][0];
            var item_name = this.store.queue[i][1];
            var item_price = Number(this.store.queue[i][2]);
            var item_quantity = Number(this.store.queue[i][3]);
            var current_date = new Date();

            tbody.innerHTML +=
                `<tr class="order_status_table">
                    <td class="order_status_table">
                        <h4>${current_date}</h4>
                    </td>
                    <td class="order_status_table">
                        <img src=${image_name} class="indexTableImage">ّ
                        <h4>${item_name}</h4>
                    </td>
                    <td class="order_status_table">
                        <h4>$${item_price}</h4>
                    </td>
                    <td class="order_status_table">
                        <h4>In Progress!</h4>
                    </td>
                </tr>`;

        }


        let clearHistoryButton = document.querySelector('.clear_history_button');
        clearHistoryButton.addEventListener('click', () => {
            this.clearHistory();
        });
    }
}
///////////////////////////////
// continue from Lab2 with Store, CheckoutButton, Cart components
class CreateFood {
    constructor(root, store) {
        this.root = root;
        this.store = store;
        this.init();
    }

    init() {

        let create_Button = document.getElementById('add_food_admin_list');
        if (create_Button) {
            create_Button.addEventListener("click", () => {
                this.createFood();
            });
        }

    }

    createFood() {
        let foods = this.store.foods || [];
        var food_image = document.getElementById('food_image').value;
        var food_name = document.getElementById('food_name').value;
        var food_price = document.getElementById('food_price').value;
        var food_description = document.getElementById('food_description').value;
        var to_push = { image: food_image, name: food_name, price: food_price, description: food_description };

        if (true) {


            var new_item = true;
            for (var i = 0; i < foods.length; i++) {
                if (to_push.name === foods[i].name) {
                    new_item = false;
                    break;
                }
            }
            if (new_item) {
                foods.push(to_push);
                this.store.foods = foods;

            }
        }
    }
}
class Inventory {
    constructor(root, store) {
        this.root = root;
        this.store = store;
        this.init();
    }

    init() {
        this.render();


    }

    destroy() {


        let hardcode_data = document.querySelector('.inventory_hardcode_button');
        if (hardcode_data) {
            hardcode_data.removeEventListener("click", () => {
                this.addDefaultMenu();
            });
        }

        let delete_Button = document.querySelectorAll('.delete_inventory_button');
        for (var i = 0; i < delete_Button.length; i++) {
            let btn = delete_Button[i];
            btn.removeEventListener('click', () => {
                let item = this.store.foods[parseInt(btn.dataset.index)];
                this.deleteItem(item);
            });
        }
    }
    deleteAllItemsMethod() {
        this.store.cartItems = [];
        this.items = [];
        this.render();
    }

    deleteItem(item_new) {

        if (this.store.foods !== null) {
            var new_menu = [];
            var to_compare = item_new.name;
            for (var i = 0; i < this.store.foods.length; i++) {
                if (this.store.foods[i].name !== to_compare) {
                    new_menu.push(this.store.foods[i]);
                }
            }
            this.store.foods = new_menu;
        }
        this.render();
    }

    addDefaultMenu() {
        let foods = this.store.foods || [];
        var chicken_alfredo = { image: "https://copymethat.blob.core.windows.net/media/one_pan_chicken_alfredo_20170111021020782111lot1cj.jpg", name: "Alfredo Chicken Penne ", price: "15.89", description: "Italian" };
        var caesar_salad = { image: "https://dw1ixebl10gex.cloudfront.net/wp-content/uploads/2016/09/Classic-HH-Recipes-Caesar-Salad-HH94-300x200.jpg", name: "Caesar Salad", price: "12.23", description: "Italian" };
        var chicken_fillet = { image: "http://flavormosaic.com/wp-content/uploads/2014/05/Salsa-Chicken-11-300x200.jpg", name: "Chicken Fillet With Salsa Sauce", price: "16.23", description: "Italian" };
        var new_item_list = [chicken_alfredo, caesar_salad,chicken_fillet];
        var new_food = true;
        for (var i = 0; i < foods.length; i++) {
            var new_food = true;
            for (var j = 0; j < new_item_list.length; j++) {
                if (foods[i].name === new_item_list[j].name) {
                    new_food = false;
                    break;
                }
            }
            if (new_food) {
                new_item_list.push(foods[i]);
            }
        }
        this.store.foods = new_item_list;
        this.render();
    }

    render() {

        let tbody = this.root.querySelector('tbody');

        tbody.innerHTML = ``;

        if (this.store.foods === null) {
            this.store.foods = [];
        }

        for (var i = 0; i < this.store.foods.length; i++) {
            var food_image = this.store.foods[i].image;
            var food_name = this.store.foods[i].name;
            var food_price = this.store.foods[i].price;
            var food_description = this.store.foods[i].description;

            tbody.innerHTML +=
                `<tr>
                    <td>
                        <img class="indexTableImage"  src=${food_image} >
                    </td>
                     <td><h4>${food_name}</h4>
                       
                    </td>
                    <td><h4>$${food_price}</h4>
                       
                    </td>
                    <td><h4>${food_description}</h4>
                    <button class="delete_inventory_button" data-index=${i}>Delete</button>
                    </td>
                
                  </tr>`;
        }


        let hardcode_button = document.querySelector('.inventory_hardcode_button');
        if (hardcode_button) {
            hardcode_button.addEventListener("click", () => {
                this.addDefaultMenu();
            });
        }

        let delete_Button = document.querySelectorAll('.delete_inventory_button');
        for (var i = 0; i < delete_Button.length; i++) {
            let btn = delete_Button[i];
            btn.addEventListener('click', () => {
                let item = this.store.foods[parseInt(btn.dataset.index)];
                this.deleteItem(item);
            });
        }

    }
}
class Menu {
    constructor(root, store, cart) {
        this.root = root;
        this.store = store;
        this.cart = cart;
        this.init();
    }

    init() {
        this.render();
    }

    render() {

        let tbody = this.root.querySelector('tbody');

        for (var i = 0; i < this.store.foods.length; i++) {
            var food_image = this.store.foods[i].image;
            var food_name = this.store.foods[i].name;
            var food_price = this.store.foods[i].price;
            var food_description = this.store.foods[i].description;

            tbody.innerHTML +=
                `<tr class="TdTrMenu">
                    <td style="border: none;text-align: left;" class="TdTrMenu"><img class="indexTableImage" width=300 height= 200 src=${food_image} ></td>
                    <td style="border: none;text-align: left;"  class="TdMenu">${food_name}</td>
                    <td style="border: none;text-align: left;"  class="TdMenu">$${food_price}</td>
                     <td style="border: none;text-align: left;"  class="TdMenu">${food_description}</td>
                     <td style="border: none;" >
							<button class="checkout-button" data-image="${food_image}" data-name="${food_name}" data-price="${food_price}"  data-quantity="1" >
								Add to the Cart
							</button>
                        </td>
                 </tr>`;
        }

        let checkoutButtons = document.querySelectorAll('.checkout-button');
        if (checkoutButtons && checkoutButtons.length) {
            for (var i = 0; i < checkoutButtons.length; i++) {
                new CheckoutButton(checkoutButtons[i], this.store, this.cart);
            }
        }
    }
}



/////////////////////////////////////////////////

// DOMContentLoaded event will allow us to run the following function when
// everything is ready. Think of the following code will only be executed by
// the end of document
document.addEventListener('DOMContentLoaded', () => {
    // use querySelector to find the table element (preferably by id selector)
    let statusTable = document.querySelector('.order_status_table');
    // // use querySelector to find the cart element (preferably by id selector)
    let cart = document.querySelector('.cart-table');
    let checkoutButtons = document.querySelectorAll('.checkout-button');
    let createFood = document.querySelector('.food_create_table_admin');
    let inventory = document.querySelector('.admin-cart-table');
    let menu = document.querySelector('.menu_table');
    let store = new Store(window.localStorage);

    if (statusTable) {
        new StatusTable(statusTable, store);
    }
    if (cart) {
        var cart_secondary = new Cart(cart, store);
    }
    if (checkoutButtons && checkoutButtons.length) {
        for (var i = 0; i < checkoutButtons.length; i++) {
            new CheckoutButton(checkoutButtons[i], store, cart_secondary);
        }
    }
    if (inventory) {
        new Inventory(inventory, store);
    }
    if (createFood) {
        new CreateFood(createFood, store);
    }
    if (menu) {
        new Menu(menu, store, cart_secondary);
    }
});
