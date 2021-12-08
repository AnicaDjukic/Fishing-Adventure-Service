<template>
  <div class="fa-page">
    <div class="main-img-fa">
      <img src="@/assets/c16.jpg" alt="" />
      <div class="tagline-fa">
        <h2>
          Enjoy in your cottage <br />
          today!
        </h2>
        <button class="book-btn">Book a cottage</button>
      </div>
    </div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark navbar-info">
      <div class="container-fluid">
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarText"
          aria-controls="navbarText"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0 menu-ol-fa">
            <li
              v-on:click="changeMenuDisplay"
              id="about-fa"
              style="border-bottom: 1px solid white"
            >
              About
            </li>
            <li v-on:click="changeMenuDisplay" id="ex-info-fa">
              Extra Information
            </li>
            <li v-on:click="changeMenuDisplay" id="loc-fa">Location</li>
            <li v-on:click="changeMenuDisplay" id="pl-fa">Pricelist</li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="menu-fa">
      <div class="menu-body-fa">
        <div class="menu-about-fa" style="text-align: justify">
          <div class="ma-top-part">
            <h3>Villa Madam</h3>
            <h5>For up to 3 people</h5>
          </div>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat. Duis aute irure dolor in
            reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
            pariatur.
          </p>
        </div>

        <div class="menu-ex-info-fa" style="display: none">
          <div class="ei-bonus">
            <h4>With every reservation you will get:</h4>
            <ul>
              <li>
                <i
                  class="fas fa-circle"
                  style="
                    color: rgb(22, 82, 161);
                    font-size: 0.5em;
                    margin-right: 2%;
                  "
                ></i>
                Towels
              </li>

              <li>
                <i
                  class="fas fa-circle"
                  style="
                    color: rgb(22, 82, 161);
                    font-size: 0.5em;
                    margin-right: 2%;
                  "
                ></i>
                Bed sheets
              </li>
              <li>
                <i
                  class="fas fa-circle"
                  style="
                    color: rgb(22, 82, 161);
                    font-size: 0.5em;
                    margin-right: 2%;
                  "
                ></i>
                Small kitchen set
              </li>
            </ul>
          </div>

          <div class="ei-rules">
            <h4>Rulebook:</h4>
            <ul>
              <li>
                <i
                  class="far fa-check-circle"
                  style="color: green; margin-right: 2%"
                ></i>
                Pets
              </li>

              <li>
                <i
                  class="far fa-times-circle"
                  style="color: red; margin-right: 2%"
                ></i>
                No smoking
              </li>

              <li>
                <i
                  class="far fa-times-circle"
                  style="color: red; margin-right: 2%"
                ></i>
                No littering
              </li>
            </ul>
          </div>
        </div>

        <div class="menu-loc-fa" style="display: none">
          <div class="loc-info">
            <h5>Bulevar Evrope 20</h5>
            <h5>Novi Sad</h5>
            <h5>45.246117, 19.816604</h5>
          </div>

          <div class="map-fa"></div>
        </div>

        <div class="menu-pl-fa" style="display: none">
          <h5>Additional services:</h5>
          <table class="table table-striped">
            <tbody>
              <tr>
                <td class="as-title">TV kable</td>
                <td><span class="as-price">$2</span></td>
              </tr>

              <tr>
                <td class="as-title">Air conditioner</td>
                <td><span class="as-price">$5</span></td>
              </tr>

              <tr>
                <td class="as-title">Parking</td>
                <td><span class="as-price">$2</span></td>
              </tr>
            </tbody>

            <tbody></tbody>
          </table>
        </div>
      </div>
    </div>

    <div class="bottom-part-fa">
      <div class="pa-title-fa">
        <h2>Previous adventures</h2>
      </div>
      <div
        id="carouselExampleIndicators"
        class="carousel slide"
        data-bs-ride="carousel"
        style="
          padding: 0% 7%;
          background: #212529;
          height: 450px;
          width: 75%;
          margin: 20px auto 0 auto;
        "
      >
        <div class="carousel-indicators">
          <button
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide-to="0"
            class="active"
            aria-current="true"
            aria-label="Slide 1"
          ></button>
          <button
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide-to="1"
            aria-label="Slide 2"
          ></button>
          <button
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide-to="2"
            aria-label="Slide 3"
          ></button>
          <button
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide-to="3"
            aria-label="Slide 4"
          ></button>
          <button
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide-to="4"
            aria-label="Slide 5"
          ></button>
        </div>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img
              src="@/assets/c16.jpg"
              class="d-block w-100"
              alt="..."
              style="object-fit: contain; height: 450px"
            />
          </div>
          <div class="carousel-item">
            <img
              src="@/assets/c16-2.jpg"
              class="d-block w-100"
              alt="..."
              style="object-fit: contain; height: 450px"
            />
          </div>
          <div class="carousel-item">
            <img
              src="@/assets/c16-3.jpg"
              class="d-block w-100"
              alt="..."
              style="object-fit: contain; height: 450px"
            />
          </div>
          <div class="carousel-item">
            <img
              src="@/assets/c16-4.jpg"
              class="d-block w-100"
              alt="..."
              style="object-fit: contain; height: 450px"
            />
          </div>
          <div class="carousel-item">
            <img
              src="@/assets/c16-5.jpg"
              class="d-block w-100"
              alt="..."
              style="object-fit: contain; height: 450px"
            />
          </div>
        </div>
        <button
          class="carousel-control-prev"
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide="prev"
          style="width: 7%"
        >
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button
          class="carousel-control-next"
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide="next"
          style="width: 7%"
        >
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>

      <div class="special-offers-fa">
        <div class="so-title-fa">
          <h2>Special Offers</h2>
        </div>
      </div>

      <div class="special-offers-fa">
        <div class="so-title-fa">
          <h2>Past experiences</h2>
        </div>

        <div class="review-fa continer">
          <div class="review-tp">
            <img class="user-rev-img" src="@/assets/c16.jpg" alt="" />
            <div class="rev-info">
              <h5>John Smith</h5>
              <h6>01.12.2021.</h6>
            </div>
            <div
              style="
                margin-left: auto;
                display: inline-flex;
                justify-content: flex-end;
                align-content: center;
              "
            >
              <i class="fas fa-star bi bi-star-fill rev-star"></i>
              <i class="fas fa-star bi bi-star-fill rev-star"></i>
              <i class="fas fa-star bi bi-star-fill rev-star"></i>
              <i class="fas fa-star bi bi-star-fill rev-star"></i>
            </div>
          </div>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat. Duis aute irure dolor in
            reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
            pariatur.
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: function () {
    return {
      entity: "",
    };
  },
  mounted() {
    window.scrollTo(0, 0);
    // axios.get("/cottage/" + this.$route.query.id).then((response) => {
    //   this.entity = response.data;

    // });
  },
  name: "FishingAdventure",
  methods: {
    changeMenuDisplay: function (event) {
      let elID = event.currentTarget.id;
      document.getElementById("about-fa").style.borderBottom = "0px";
      document.getElementById("ex-info-fa").style.borderBottom = "0px";
      document.getElementById("loc-fa").style.borderBottom = "0px";
      document.getElementById("pl-fa").style.borderBottom = "0px";

      document.getElementById(elID).style.borderBottom = "1px solid white";

      if (elID == "about-fa") {
        document.querySelector(".menu-about-fa").style.display = "block";
        document.querySelector(".menu-ex-info-fa").style.display = "none";
        document.querySelector(".menu-loc-fa").style.display = "none";
        document.querySelector(".menu-pl-fa").style.display = "none";
      } else if (elID == "ex-info-fa") {
        document.querySelector(".menu-about-fa").style.display = "none";
        document.querySelector(".menu-ex-info-fa").style.display = "flex";
        document.querySelector(".menu-loc-fa").style.display = "none";
        document.querySelector(".menu-pl-fa").style.display = "none";
      } else if (elID == "loc-fa") {
        document.querySelector(".menu-about-fa").style.display = "none";
        document.querySelector(".menu-ex-info-fa").style.display = "none";
        document.querySelector(".menu-loc-fa").style.display = "inline-flex";
        document.querySelector(".menu-pl-fa").style.display = "none";
      } else if (elID == "pl-fa") {
        document.querySelector(".menu-about-fa").style.display = "none";
        document.querySelector(".menu-ex-info-fa").style.display = "none";
        document.querySelector(".menu-loc-fa").style.display = "none";
        document.querySelector(".menu-pl-fa").style.display = "block";
      } else {
        document.querySelector(".menu-about-fa").style.display = "none";
        document.querySelector(".menu-ex-info-fa").style.display = "none";
        document.querySelector(".menu-loc-fa").style.display = "none";
        document.querySelector(".menu-pl-fa").style.display = "none";
      }
    },
  },
};
</script>

<style scoped src="@/css/fishingAdventure.css"></style>
