<template>
  <section>
    <div class="footer">
      <h4>About Us</h4>
      <p>
        We are students at the Faculty of Technical Sciences in Novi Sad
        <br />
        trying to make this project for a course in Internet Software
        Architecture
      </p>
      <p>
        Made with <i class="fa fa-heart-o"></i> by
        <a href="https://github.com/AnicaDjukic"
          ><i class="fa fa-github"> Anica</i></a
        >,
        <a href="https://github.com/ABjelobaba"
          ><i class="fa fa-github"> Aleksandar</i></a
        >
        and
        <a href="https://github.com/leakalmar"
          ><i class="fa fa-github"> Lea</i></a
        >
      </p>
      <p>
        You can find our project
        <a href="https://github.com/leakalmar/FishingAdventureService">here</a>
        .
      </p>
    </div>
  </section>
</template>

<script>
export default {
  name: "Footer",
};
</script>

<style scoped>
.footer {
  width: 100%;
  text-align: center;
  padding: 30px 0;
  z-index: 0;
  background-color: #212529;
}

.footer p {
  color: rgb(190, 190, 190);
  padding: 0;
}

.footer p a {
  color: #36b8f4;
}

.footer h4 {
  margin-bottom: 25px;
  margin-top: 20px;
  font-weight: 600;
  color: #36b8f4;
}

.icons .fa {
  color: #f44336;
  margin: 0 13px;
  cursor: pointer;
  padding: 18px 0;
}

.fa-heart-o {
  color: #f44336;
}
</style>
