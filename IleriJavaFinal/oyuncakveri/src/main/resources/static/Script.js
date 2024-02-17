function ekle() {
    const ad = document.getElementById('eklead').value;
    const desi = document.getElementById('ekledesi').value;
    const alisTarihi = document.getElementById('eklealisTarihi').value;
    const alisFiyati = document.getElementById('eklealisFiyati').value; // corrected ID
    const tur = document.getElementById('ekletur').value;
    const notlar = document.getElementById('eklenotlar').value;
    const oyuncakDto = {
        ad: ad,
        desi: desi,
        alisTarihi: alisTarihi,
        alisFiyati: alisFiyati,
        tur: tur,
        notlar: notlar
    };
    sendRequest('/ekle', 'POST', oyuncakDto)
        .then(response => {
            alert(response);
        })
        .catch(error => {
            console.error(error);
            alert('Oyuncak eklenirken bir hata oluştu.');
        });
}
function sendRequest(url, method, data) {
    return fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        });
}
function getir() {

    const id = document.getElementById('Id').value;
    fetch('/getir?id=' + id)
        .then(response => response.json())
        .then(data => {
            updatePage(data);
        })
        .catch(error => {
            console.error('Hata:', error);
        });
}
function updatePage(oyuncak) {
    updateElement('getiroyuncakAd', oyuncak.ad);
    updateElement('getiralisTarihi', oyuncak.alisTarihi);
    updateElement('getirdesi', oyuncak.desi);
    updateElement('getirtur', oyuncak.tur);
    updateElement('getirnotlar', oyuncak.notlar);
    updateElement('getiralisFiyati', oyuncak.alisFiyati);
    updateElement('getiroyuncakID', oyuncak.id);
}

function updateElement(elementId, text) {
    var element = document.getElementById(elementId);
    if (element) {
        element.innerText = text;
    } else {
        console.error('Element bulunamadı: ' + elementId);
    }
}
function guncelle() {
    const oyuncakId = prompt('Güncellenecek oyuncak ID\'sini girin:');

    if (!oyuncakId) {
        alert('Geçerli bir ID girilmedi.');
        return;
    }

    const ad = document.getElementById('guncelleAd').value;
    const desi = document.getElementById('guncelleDesi').value;
    const alisTarihi = document.getElementById('guncelleAlisTarihi').value;
    const alisFiyati = document.getElementById('guncelleAlisFiyati').value;
    const tur = document.getElementById('guncelleTur').value;
    const notlar = document.getElementById('guncelleNotlar').value;

    const oyuncakDto = {
        ad: ad,
        desi: desi,
        alisTarihi: alisTarihi,
        alisFiyati: alisFiyati,
        tur: tur,
        notlar: notlar
    };

    sendRequest(`/guncelle/${oyuncakId}`, 'PUT', oyuncakDto)
        .then(response => {
            alert(response);
        })
        .catch(error => {
            console.error(error);
            alert('Oyuncak güncellenirken bir hata oluştu.');
        });
}
function sil() {
    const id = document.getElementById('oyuncakId').value;

    const now = new Date();

    fetch(`/sil?id=${id}`, {
        method: 'DELETE',
    })
        .then(response => response.text())
        .then(data => {
            alert(data);
        })
        .catch(error => {
            console.error('Hata oluştu:', error);
        });
}

function listele() {
    fetch("/listele")
        .then(response => response.json())
        .then(data => {
            const oyuncakListesiElement = document.getElementById("oyuncakListesi");

            if (oyuncakListesiElement !== null) {
                oyuncakListesiElement.innerHTML = ''; // Önce mevcut içeriği temizle

                const table = document.createElement("table");
                table.classList.add("table", "table-bordered", "table-striped"); // Bootstrap sınıflarını ekle

                const headerRow = document.createElement("thead");
                const headerRowContent = ["ID", "Ad", "Alış Tarihi", "Alış Fiyatı", "Tür", "Desi", "Notlar"];

                headerRowContent.forEach(headerText => {
                    const headerCell = document.createElement("th");
                    headerCell.textContent = headerText;
                    headerRow.appendChild(headerCell);
                });

                table.appendChild(headerRow);

                const tbody = document.createElement("tbody");
                data.forEach(oyuncak => {
                    const oyuncakDataRow = document.createElement("tr");
                    const values = [oyuncak.id, oyuncak.ad, oyuncak.alisTarihi, oyuncak.alisFiyati, oyuncak.tur, oyuncak.desi, oyuncak.notlar];

                    values.forEach(value => {
                        const dataCell = document.createElement("td");
                        dataCell.textContent = value;
                        oyuncakDataRow.appendChild(dataCell);
                    });

                    tbody.appendChild(oyuncakDataRow);
                });

                table.appendChild(tbody);

                oyuncakListesiElement.appendChild(table);

                const searchInputs = [
                    document.getElementById("searchId"),
                    document.getElementById("searchAd"),
                    document.getElementById("searchAlisTarihi"),
                    document.getElementById("searchAlisFiyatı"),
                    document.getElementById("searchTur"),
                    document.getElementById("searchDesi"),
                    document.getElementById("searchNotlar")
                ];

                searchInputs.forEach((searchInput, index) => {
                    searchInput.addEventListener("input", function () {
                        const filters = searchInputs.map(input => input.value.toLowerCase());

                        const rows = tbody.getElementsByTagName("tr");

                        for (let i = 0; i < rows.length; i++) {
                            const cells = rows[i].getElementsByTagName("td");
                            let shouldShowRow = true;

                            for (let j = 0; j < filters.length; j++) {
                                const columnIndex = parseInt(searchInputs[j].getAttribute("data-column"));
                                const cellText = cells[columnIndex].textContent.toLowerCase();

                                if (!cellText.includes(filters[j])) {
                                    shouldShowRow = false;
                                    break;
                                }
                            }

                            rows[i].style.display = shouldShowRow ? "" : "none";
                        }
                    });
                });



            }
        })
        .catch(error => {
            console.error("Hata:", error);
        });

    var button = document.querySelector('.fixed-size-btn');
    button.innerHTML = "Oyuncak listesini yenile";
}