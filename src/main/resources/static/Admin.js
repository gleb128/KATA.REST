const requestURL = "http://localhost:8080/admin/users"
const adminTable =  document.getElementById("AdminTable")

async function loadAllUsers() {
    try {
        const response = await fetch(requestURL)
        const users = await response.json();
        const tableBody = document.querySelector("#AdminTable tbody")
        tableBody.innerHTML = '';

        users.forEach(user => {

            let roles = user.roles.map(role => role.name.replace('ROLE_', '')).join(',')
            const row = document.createElement('tr');

            const idCell = document.createElement('td');
            idCell.textContent = user.id;

            const nameCell = document.createElement("td")
            nameCell.textContent = user.name;

            const lastNameCell = document.createElement("td");
            lastNameCell.textContent = user.lastName;

            const ageCell = document.createElement('td');
            ageCell.textContent = user.age;

            const usernameCell = document.createElement('td')
            usernameCell.textContent = user.username;

            const rolesCell = document.createElement('td');
            rolesCell.textContent = roles

            row.appendChild(idCell);
            row.appendChild(nameCell);
            row.appendChild(lastNameCell);
            row.appendChild(ageCell);
            row.appendChild(usernameCell);
            row.appendChild(rolesCell)

            tableBody.append(row)
        });
    } catch (error) {
        console.error('Couldn\'t load user: ', error);
    }
} loadAllUsers()





