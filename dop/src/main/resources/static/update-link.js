(async function(){
    let elem = document.getElementById("bt-add-link");
    elem.addEventListener("click", onHandleClick);
    let idPath = location.pathname.split('/')[2]

    const response = await fetch("http://localhost:8080/api/get/"+ idPath, {
            method: "GET",
            headers: { "Accept": "application/json" }
        });

    if (response.ok === true) {

        let el = await response.json();

        document.getElementById("link-name").value = el.mainLink;
        document.getElementById("link-desc").value = el.desс;
    }
    async function onHandleClick() {
        const response = await fetch("http://localhost:8080/api/", {
            method: "PUT",
            headers: { "Accept": "application/json", "Content-Type": "application/json" },
            body: JSON.stringify({
                "link-name": document.getElementById("link-name").value,
                "link-desc": document.getElementById("link-desc").value,
                "link-id": idPath,
            })
        });
        window.location.href = '/';
    }
})();