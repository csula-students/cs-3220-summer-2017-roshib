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
            cartItems.push([this.root.dataset.image, this.root.dataset.name,Number(this.root.dataset.price), Number(this.root.dataset.quantity)]);
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

// DOMContentLoaded event will allow us to run the following function when
// everything is ready. Think of the following code will only be executed by
// the end of document
document.addEventListener('DOMContentLoaded', () => {
    // use querySelector to find the table element (preferably by id selector)
    let statusTable = document.querySelector('.order_status_table');
    // // use querySelector to find the cart element (preferably by id selector)
    let cart = document.querySelector('.cart-table');
    let checkoutButtons = document.querySelectorAll('.checkout-button');

    let store = new Store(window.localStorage);

    if (statusTable) {
        new StatusTable(statusTable, store);
    }
    if (cart) {
        new Cart(cart, store);
    }
    if (checkoutButtons && checkoutButtons.length) {
        for (var i = 0; i < checkoutButtons.length; i++) {
            new CheckoutButton(checkoutButtons[i], store);
        }
    }
});