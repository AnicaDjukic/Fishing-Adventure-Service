<template>
  <div>
    <div class="title">
      <h1>Reviews</h1>
      <i
        class="far fa-star fa-4x"
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
                Rating
              </a>
              <ul
                class="dropdown-menu text-center dropdown-menu-dark"
                aria-labelledby="navbarScrollingDropdown"
              >
                <span class="dropdown-item" style="text-align:left"> 
                    <input type="checkbox" style="margin-right:10px; margin-left:5px">
                    <label style="font-size: 17px;">1 star</label>
                </span>
               <span class="dropdown-item" style="text-align:left"> 
                    <input type="checkbox" style="margin-right:10px; margin-left:5px">
                    <label style="font-size: 17px">2 stars</label>
                </span>
                <span class="dropdown-item" style="text-align:left"> 
                    <input type="checkbox" style="margin-right:10px; margin-left:5px">
                    <label style="font-size: 17px">3 stars</label>
                </span>
                <span class="dropdown-item" style="text-align:left"> 
                    <input type="checkbox" style="margin-right:10px; margin-left:5px">
                    <label style="font-size: 17px">4 stars</label>
                </span>
                <span class="dropdown-item"  style="text-align:left"> 
                    <input type="checkbox" style="margin-right:10px; margin-left:5px">
                    <label style="font-size: 17px">5 stars</label>
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
                    <th>Service</th>
                    <th>Rating</th>
                    <th>Review </th>
                    <th>Date posted </th>
                    <th colspan="2">Approve?</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="review in reviews" :key="review.id" v-bind:review="review">
                    <td>{{review.serviceName}}</td>
                    <td><i class="fas fa-star" style="font-size: 100%"></i> {{review.rating}}</td>
                    <td >
                        <div style="text-align: left">  {{review.content}} </div>
                      
                        <div style="text-align: right"> <span class="badge bg-secondary"> by {{review.clientFullName}} </span></div>
                    </td>
                    <td>{{review.datePosted}} </td>
                    <td>
                        <button class="green-btn" v-on:click="approveReview(review)"
                        > Yes</button>
                    </td>
                    <td>
                        <button class="black-btn" v-on:click="rejectReview(review)"
                        > No</button>
                    </td>

                </tr>
            </tbody>
        </table>
    </div>
  </div>
 
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;
export default {
  data: function () {
    return {
      searchText: "",
      searchResults: [],
      reviews: []
    };
  },
  mounted: function () {
    axios
      .get("/review/getReviewsForAdmin", {
        headers: {
          "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.reviews = res.data;
      });
  },
  methods: {
    approveReview: function (review) {
        axios
          .put("/review/approveReview/", review, {
            headers: {
              "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          })
          .then(() => {
            this.reviews.splice(this.reviews.indexOf(review), 1);
            this.$toast.show(
            "Review approved.",
            {
              duration: 3000,
            }
          );
          });
    },
    rejectReview: function (review) {
        axios
          .put("/review/rejectReview/", review, {
            headers: {
              "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          })
          .then(() => {
            this.reviews.splice(this.reviews.indexOf(review), 1);
            this.$toast.show(
            "Review rejected.",
            {
              duration: 3000,
            }
          );
          });
    },
  },
};
</script>

<style scoped src="@/css/mycottages.css"></style>
<style scoped src="@/css/admin.css"></style>