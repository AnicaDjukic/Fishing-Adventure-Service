<template>
  <div>
    <h6>(Optional)</h6>
    <h6>Plase fill extra information:</h6>

    <div style="margin-top: 5%">
      <div class="input-header">
        <h6>Navigation equipment</h6>
        <i class="far fa-plus-square" v-on:click="addEquipment"></i>
      </div>
      <table class="table">
        <thead>
          <th>Name</th>
          <th>Delete</th>
        </thead>
        <tbody>
          <tr v-for="equip in newEquipment" :key="equip.id">
            <td>
              <input
                placeholder="Equipment name"
                type="text"
                class="login-inputs"
                style="border: 0; margin: 0"
                v-model="equip.name"
                v-on:change="emit"
              />
            </td>
            <td>
              <i
                class="far fa-trash-alt"
                style="color: #832626; cursor: pointer"
                v-on:click="removeEquipment(equip.id)"
              ></i>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  props: ["navEquipment"],
  name: "NewBoatModalFishingEq",
  data: function () {
    return {
      newEquipment: [],
      equipId: 0,
    };
  },
  mounted() {
    if (this.navEquipment) {
      this.newEquipment = this.navEquipment;

      let maxId = 0;
      for (let equip of this.navEquipment) {
        if (parseInt(equip.id) > maxId) {
          maxId = parseInt(equip.id);
        }
      }
      this.equipId = maxId + 1;
    }
  },
  methods: {
    emit: function () {
      let result = true;
      for (let equip of this.newEquipment) {
        if (!equip.name) {
          result = false;
        }
      }
      console.log(result);

      let retVal = {
        data: this.newEquipment,
        result: result,
      };
      this.$emit("navequipupdated", retVal);
    },
    addEquipment: function () {
      let newEquip = {
        equipId: this.equipId,
        name: "",
      };
      this.equipId = this.equipId + 1;
      this.newEquipment.push(newEquip);
      this.emit();
    },
    removeEquipment: function (id) {
      for (let equip of this.newEquipment) {
        if (equip.id === id) {
          this.newEquipment.pop(equip);
          break;
        }
      }
      this.emit();
    },
  },
};
</script>

<style scoped>
.login-inputs {
  display: block;
  margin: 15px auto 0 auto;
  background-color: transparent;
  width: 100%;
  border-width: 0;
  border-bottom-width: 1px;
  border-color: white;
  padding: 2px 20px;
  color: white;
  font-size: 17px;
}

.table {
  color: white;
  margin-top: 2%;
}

.input-header {
  color: white;
  display: flex;
  justify-content: space-around;
  background-color: rgb(33, 37, 41);
  align-items: center;
}

.modal-body h6 {
  margin: 0;
  text-align: center;
}

.modal-body p {
  text-align: center;
}
</style>
