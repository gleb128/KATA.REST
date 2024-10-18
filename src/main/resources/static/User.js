async function loadUserInfo() {
    try {
        const response = await fetch('/userAPI/user')
        const user = await response.json();
        let tableBody = document.querySelector("#UserTable tbody")
        let roles = user.roles.map(role => role.name.replace('ROLE_', '')).join(',')
        const row = document.createElement('tr');

        const idCell = document.createElement('td');
        idCell.textContent = user.id;

        const nameCell = document.createElement('td');
        nameCell.textContent = user.name;

        const lastNameCell = document.createElement("td")
        lastNameCell.textContent = user.lastName;

        const ageCell = document.createElement('td');
        ageCell.textContent = user.age;

        const usernameCell = document.createElement('td');
        usernameCell.textContent = user.username;

        const rolesCell = document.createElement('td');
        rolesCell.textContent = roles;

        row.appendChild(idCell);
        row.appendChild(nameCell);
        row.appendChild(lastNameCell);
        row.appendChild(ageCell);
        row.appendChild(usernameCell);
        row.appendChild(rolesCell);

        tableBody.appendChild(row)

    } catch (error) {
        console.error('Couldn\'t load user: ', error);
    }
}

loadUserInfo();

async function loadUserNavbar() {
    try {
        const response = await fetch('/userAPI/user');
        const user = await response.json();
        document.getElementById('usernameSpan').textContent = user.username;
        document.getElementById('rolesSpan').textContent = "with roles: "
        document.getElementById('rolesSpanTrue').textContent = user.roles.map(role => role.name.replace('ROLE_', '')).join(',')
    } catch (error) {
        console.error('Error loading navbar', error);
    }
}

loadUserNavbar();