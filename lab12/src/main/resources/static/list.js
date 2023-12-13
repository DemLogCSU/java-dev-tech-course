(async function(){

    let list = [];


    const response = await fetch("/api/products/", {
        method: "GET",
        headers: { "Accept": "application/json" }
    });
    if (response.ok === true) {

        list = await response.json();

        let product;
        for (product of list) {
            append_table_list(product);
        }
        let elem = document.getElementById("btn-re-add");
        elem.addEventListener("click", onRedirect);
    }
    async function onRedirect() {

        window.location.href = '/products/add';
    }
    async function onDelete(event) {
        const response = await fetch(`/api/products/delete/${event.target.id}`,{
            method: "POST",
            headers: { "Accept": "application/json" }
        });
        if (response.ok === true) {
            alert("Successfully deleted!");
            window.location.href = '/products/list';
        }
    }

    async function onUpdate(event) {
        const response = await fetch(`/api/products/update/${event.target.id}`,{
            method: "PUT",
            headers: { "Accept": "application/json" }
        });
        if (response.ok === true) {
            alert("Successfully changed!");
        }
    }

    function append_table_list(product) {
        let products = document.getElementById("shopping-list");
        let child = document.createElement("div")
        child.innerHTML += `${product.name}`;
        child.innerHTML += `<input type="button" class="btn_del" id="${product.id}" value="Delete">`;
        child.innerHTML += `<input type="checkbox" class="btn_upd" id="${product.id}" ${product.isPurchased ? 'checked' : ''} value="${product.isPurchased}">`
        products.appendChild(child);
        child.querySelector(".btn_del").addEventListener("click", onDelete);
        child.querySelector(".btn_upd").addEventListener("change", onUpdate);
    }
})();