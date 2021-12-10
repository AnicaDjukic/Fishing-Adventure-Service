<template>
  <div>
    <div class="title">
      <h1>All users</h1>
      <i
        class="far fa-users fa-3x"
        style="font-family: 'Font Awesome 5 Pro'"
      ></i>
    </div>
    <div
      style="
        background-color: #212529;
        padding: 15px;
        text-align: -webkit-center;
      "
    >
      <div
        class="container w-100 row row-cols-1 row-cols-sm-1 row-cols-md-4"
        style="justify-content: space-evenly; align-items: center"
      >
        <div class="col-md-2">
          <button
            type="button"
            class="btn btn-outline-primary text-nowrap me-2"
            data-bs-toggle="modal"
          >
            Add new admin
          </button>
        </div>
        <div class="col-md-3">
          <input
            class="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
            v-model="searchText"
            v-on:keyup="searchUsers"
            v-on:click="searchUsers"
          />
        </div>
        <div class="col-md-4">
          <div style="display: flex">
            <div
              class="rating-div form-control"
              style="min-width: 115px;"
              v-on:click="searchUsers"
              
            >
               <div class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarScrollingDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                style="color: white; margin: -2% 0;"
              >
                User type
              </a>
              <ul
                class="dropdown-menu text-center dropdown-menu-dark"
                aria-labelledby="navbarScrollingDropdown"
              >
                <span class="dropdown-item" style="text-align:left"> 
                    <input type="checkbox" style="margin-right:10px; margin-left:5px">
                    <label style="font-size: 17px;">Clients</label>
                </span>
               <span class="dropdown-item" style="text-align:left"> 
                    <input type="checkbox" style="margin-right:10px; margin-left:5px">
                    <label style="font-size: 17px">Cottage owners</label>
                </span>
                <span class="dropdown-item" style="text-align:left"> 
                    <input type="checkbox" style="margin-right:10px; margin-left:5px">
                    <label style="font-size: 17px">Boat owners</label>
                </span>
                <span class="dropdown-item" style="text-align:left"> 
                    <input type="checkbox" style="margin-right:10px; margin-left:5px">
                    <label style="font-size: 17px">Fishing instructors</label>
                </span>
                <span class="dropdown-item"  style="text-align:left"> 
                    <input type="checkbox" style="margin-right:10px; margin-left:5px">
                    <label style="font-size: 17px">Administrators</label>
                </span>
                
              </ul>
            </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div>
        <table class="table-users">
            <thead>
                <tr>
                    <th>Role</th>
                    <th v-on:click="sortByName" id="name-th">Name <i class="fa fa-sort"></i></th>
                    <th v-on:click="sortBySurname" id="surname-th">Surname <i class="fa fa-sort"></i></th>
                    <th v-on:click="sortByUsername" id="email-th">Email <i class="fa fa-sort"></i></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="user in users" :key="user.id" v-bind:user="user">
                    <td>
                        <i v-if="user.userType == 'CLIENT'" class="fas fa-user fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="user.userType == 'ADMINISTRATOR'" class="fas fa-cog fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="user.userType == 'FISHING_INSTRUCTOR'" class="fas fa-fish fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="user.userType == 'COTTAGE_OWNER'" class="fas fa-home fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="user.userType == 'BOAT_OWNER'" class="fas fa-anchor fa-lg" style="color:#003148" aria-hidden="true"></i>
                        </td>
                    <td>{{user.name}}</td>
                    <td>{{user.surname}}</td>
                    <td>{{user.email}}</td>
                    <td>
                         <button class="black-btn" v-if="user.userType=='ADMINISTRATOR'"  disabled ><i class="fa fa-trash-o" aria-hidden="true"></i> Delete</button>
                        <button class="black-btn" v-else ><i class="fa fa-trash-o" aria-hidden="true"></i> Delete</button>
                    </td>
                </tr>
                <!-- <tr v-if="searchResults.length == 0">
                    <td colspan="6"><h3  style="text-align:center" >No users found</h3></td>
                </tr> -->
            </tbody>
        </table>
      
    </div>
  </div>
 
</template>

<script>
export default {
  data: function () {
    return {
      searchText: "",
      searchResults: [],
      users: [
        {
          id: "0",
          name: "Simon",
          surname: "Smith",
          email: "simon_smith@gmail.com",
          userType: "CLIENT",
        },
        {
          id: "1",
          name: "Curtis",
          surname: "Lee",
          email: "curtis_lee@gmail.com",
          userType: "BOAT_OWNER",
        },
        {
          id: "2",
          name: "Amy",
          surname: "Larsson",
          email: "simon_smith@gmail.com",
          userType: "COTTAGE_OWNER",
        },
        {
          id: "3",
          name: "Nick",
          surname: "Ness",
          email: "nick@gmail.com",
          userType: "FISHING_INSTRUCTOR",
        },
        {
          id: "4",
          name: "Mike",
          surname: "Garvey",
          email: "mike_g@gmail.com",
          userType: "ADMINISTRATOR",
        }
      ]
    };
  },
  methods: {
    searchUsers: function () {

    },
  },
};
</script>

<style scoped src="@/css/mycottages.css"></style>
<style scoped>

.table-users {
    display: inline-table;
    border-collapse: collapse;
    width: 75%;
    margin: 50px 0 30px 0;
    font-size: 20px;
    text-align: center;
    border-radius: 5px 5px 0 0;
    overflow: hidden;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
    position: relative;
}

.table-users thead tr {
    background-color: #003148;
    color: white;
    text-align: center;
}

.table-users th {
    cursor: pointer;
}

.table-users th,
.table-users td {
    padding: 12px 15px;
    color: white;
}

.table-users tbody tr {
    border-bottom: 1px solid #dddddd;
}

.table-users tbody tr:hover {
    transition: 0.5s;
    background-color: #dddddd;
    color: #003148;
    cursor: pointer;
}

.table-users tbody tr:hover td {
    color: #003148;
}

.table-users tbody tr:last-of-type {
    border-bottom: 2px solid #003148;
}

.black-btn {
    display: inline-block;
    text-decoration: none;
    color: #AD1313;
    font-weight: bold;
    border: 1px solid #AD1313;
    padding: 10px 30px;
    font-size: 15px;
    margin: 4px;
    background: transparent;
    position: relative;
    cursor: pointer;
}

.black-btn:disabled {
    border-color: gray;
    color: gray;
    cursor: default;
}

.black-btn:disabled:hover {
    border-color: gray;
    color: gray;
    background-color: transparent;
    cursor: default;
}

.black-btn:hover {
    background: #AD1313;
    transition: 0.5s;
    color: white;
}

</style>


