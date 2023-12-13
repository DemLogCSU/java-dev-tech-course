(async function(){
    console.log(0);
    let elem = document.getElementById("bt-add-product");
    elem.addEventListener("click", onHandleClick);
    async function onHandleClick() {
        console.log(1);
        const response = await fetch("/api/products/add", {
            method: "POST",
            headers: { "Accept": "application/json", "Content-Type": "application/json" },
            body: JSON.stringify({
                "product-name": document.getElementById("product-name").value,
            })
        });
        console.log(2);
        if (response.ok === true) {
            alert("Successfully added!");
            window.location.href = '/products/list';
        }
    }
})();