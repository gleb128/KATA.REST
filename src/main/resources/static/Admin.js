const requestURL = "http://localhost:8080/admin/users"
const adminTable = document.getElementById("AdminTable")

async function loadAllUsers() {
    try {
        const response = await fetch(requestURL)
        const users = await response.json();
        const tableBody = document.querySelector("#AdminTable tbody")
        tableBody.innerHTML = '';

        users.forEach(user => {

            let roles = user.roles.map(role => role.name.replace('ROLE_', '')).join(', ')
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

            const editCell = document.createElement('td')
            const editButton = document.createElement('button')
            editButton.textContent = 'Edit';
            editButton.classList.add('btn', 'btn-primary')
            /// event dobavit'
            editCell.appendChild(editButton)

            const deleteCell = document.createElement('td')
            const deleteButton = document.createElement('button')
            deleteButton.textContent = 'Delete';
            deleteButton.classList.add('btn', 'btn-danger')
            deleteButton.addEventListener('click', () => deleteUser(user.id));
            deleteCell.appendChild(deleteButton)


            row.appendChild(idCell);
            row.appendChild(nameCell);
            row.appendChild(lastNameCell);
            row.appendChild(ageCell);
            row.appendChild(usernameCell);
            row.appendChild(rolesCell)
            row.appendChild(editCell)
            row.appendChild(deleteCell)

            tableBody.append(row)
        });
    } catch (error) {
        console.error('Couldn\'t load user: ', error);
    }
}

loadAllUsers()

const newUserForm = document.getElementById('addUserForm');
newUserForm.addEventListener('submit', (event) => {
    addNewUser();
})

function createUserFromForm() {
    return {
        name: document.getElementById('name').value,
        lastName: document.getElementById('lastName').value,
        age: parseInt(document.getElementById('age').value),
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        roles: Array.from(document.getElementById('roles').selectedOptions).map(option => ({id: parseInt(option.value)}))
    }
}

async function addNewUser() {
    let entity = createUserFromForm();
    fetch('/admin/users', {
        method: 'POST', body: JSON.stringify(entity), headers: {
            "content-type": 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log("User added", data);
            document.querySelector('#users-tab').click();
            loadAllUsers()
        })
        .catch(error => {
            console.error("Error adding user:", error);
        });
}

async function deleteUser(id) {
    try {
        const response = await fetch(`/admin/users/${id}`, {method: 'DELETE'})
        if (response.ok) {
            console.log(`user with ${id} was deleted `)
            await loadAllUsers()
        } else {
            console.error('Error deleting user')
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

