(async function(){
    let elem = document.getElementById("bt-add-link");
    elem.addEventListener("click", onHandleClick);
    async function onHandleClick() {
        const response = await fetch("http://localhost:8080/api/", {
            method: "POST",
            headers: { "Accept": "application/json", "Content-Type": "application/json" },
            body: JSON.stringify({
                "link-name": document.getElementById("link-name").value,
                "link-desc": document.getElementById("link-desc").value,
            })
        });
        if (response.ok === true) {
            window.location.href = '/';
        }
    }
})();