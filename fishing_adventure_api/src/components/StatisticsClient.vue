<template>
  <div>
    <div
      class="card mb-3 bg-dark mt-3 text-white"
      style="width: 80%; margin: auto"
    >
      <div class="row g-0">
        <div class="col-md-4 shadow-none navbar-container">
          <nav class="navbar navbar-expand-lg navbar-dark bg-dark navbar-info">
            <div class="container-fluid">
              <button
                class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbar-text"
                aria-controls="navbar-text"
                aria-expanded="false"
                aria-label="Toggle navigation"
              >
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbar-text">
                <ul class="navbar-nav mb-2 mb-lg-0 menu-ol" id="navbar-ul">
                  <li v-on:click="changeMenuDisplay" id="stats" class="active">
                    <p style="margin: 1rem 0">Stats</p>
                  </li>
                  <li v-on:click="changeMenuDisplay" id="terms">
                    <p style="margin: 1rem 0">Terms</p>
                  </li>
                </ul>
              </div>
            </div>
          </nav>
        </div>
        <div class="col-md-8 shadow-none main main-stats" name="main-col">
          <div class="points-container">
            <h4 style="margin-right: auto">Gold</h4>
            <h3 style="margin-right: 10px">{{ points }}</h3>
            <p>POINTS</p>
          </div>
          <div class="penalties-container">
            <h4 style="margin: 0; margin-right: auto">Penalties</h4>
            <h3 style="margin: 0; margin-right: 10px">{{ penalties }}</h3>
            <p>PENALTIES</p>
          </div>
          <div class="reservation-stats">
            <div class="stat">
              <img style="height: 4rem" src="@/assets/house4.png" />
              <h3>{{ cottageReservations }}</h3>
            </div>
            <div class="stat">
              <img style="height: 4rem" src="@/assets/yatch.png" />
              <h3>{{ boatReservations }}</h3>
            </div>
            <div class="stat">
              <img style="height: 4rem" src="@/assets/fishing1.png" />
              <h3>{{ adventureReservations }}</h3>
            </div>
          </div>
        </div>
        <div class="col-md-8 shadow-none main main-terms" name="main-col">
          <div>
            <h3>Policy</h3>
            <p>
              We want to give you bast offers for crating new memories. For
              every reservation on our website you will get points. Try to take
              as many points possible, because they can ensoure you best deals.
            </p>
            <table class="table table-striped">
              <thead>
                <th>Category</th>
                <th>Discount</th>
                <th>Points required</th>
              </thead>
              <tbody>
                <tr v-for="category of userCategories" :key="category.id">
                  <td>{{ category.name }}</td>
                  <td>{{ category.percentage }}%</td>
                  <td>{{ category.points }}</td>
                </tr>
              </tbody>
            </table>
            <h3>Penalties</h3>
            <p>
              Any advertiser may request the sanctioning of users if there is a
              reason for that. In that case, based on the stated reason, the
              administrator decides whether the sanctions will be executed. The
              sanction is implemented in the form of a penalty.
            </p>
            <p>
              If the user receives 3 penalties, the creation of new reservations
              will be prohibited. Penalties are cleared every first of the
              month.
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: function () {
    return {
      points: 50,
      penalties: 2,
      cottageReservations: 5,
      boatReservations: 1,
      adventureReservations: 2,
      userCategories: [
        {
          id: 1,
          name: "GOLD",
          percentage: 15,
          points: 1000,
        },
        {
          id: 2,
          name: "SILVER",
          percentage: 12,
          points: 500,
        },
        {
          id: 3,
          name: "BRONZE",
          percentage: 7,
          points: 200,
        },
      ],
    };
  },
  methods: {
    changeMenuDisplay: function (event) {
      document.getElementById("stats").classList.remove("active");
      document.getElementById("terms").classList.remove("active");
      let elID = event.currentTarget.id;
      document.getElementById(elID).classList.add("active");

      if (elID == "stats") {
        document.querySelector(".main-stats").style.display = "block";
        document.querySelector(".main-terms").style.display = "none";
      } else if (elID == "terms") {
        document.querySelector(".main-stats").style.display = "none";
        document.querySelector(".main-terms").style.display = "block";
      } else {
        document.querySelector(".main-stats").style.display = "none";
        document.querySelector(".main-terms").style.display = "none";
      }
    },
  },
};
</script>

<style scoped src="@/css/statistics.css"></style>
