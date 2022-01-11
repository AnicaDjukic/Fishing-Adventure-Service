<template>
  <div>
    <h6>(Optional)</h6>
    <h6>Plase fill extra information about your cottage:</h6>

    <div style="margin-top: 5%">
      <div class="input-header">
        <h6>Rooms</h6>
        <i class="far fa-plus-square" v-on:click="addRoom"></i>
      </div>
      <table class="table">
        <thead>
          <th>Room</th>
          <th>Number of beds</th>
          <th>Delete</th>
        </thead>
        <tbody>
          <tr v-for="room in rooms" :key="room.id">
            <td>
              <label
                type="text"
                class="login-inputs"
                style="border: 0; margin: 0; white-space: nowrap"
                >Room {{ room.roomId }}</label
              >
            </td>
            <td>
              <input
                placeholder="Beds"
                type="number"
                class="login-inputs"
                style="border: 0; margin: 0"
                v-model="room.bedNumber"
                v-on:change="emit"
              />
            </td>
            <td>
              <i
                class="far fa-trash-alt"
                style="color: #832626; cursor: pointer"
                v-on:click="removeRoom(room.roomId)"
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
  props: ["rooms"],
  data: function () {
    return {
      newRooms: [],
      roomId: 0,
    };
  },
  mounted() {
    if (this.rooms) {
      this.newRooms = this.rooms;
    }

    let maxId = 0;
    for (let room of this.rooms) {
      if (parseInt(room.roomId) > maxId) {
        maxId = parseInt(room.roomId);
      }
    }
    this.roomId = maxId + 1;
  },
  methods: {
    emit: function () {
      let result = true;
      for (let room of this.newRooms) {
        if (!room.bedNumber) {
          result = false;
        }
      }

      let retVal = {
        data: this.newRooms,
        result: result,
      };
      this.$emit("roomupdated", retVal);
    },
    addRoom: function () {
      let newRoom = {
        roomId: this.roomId,
        bedNumber: "",
      };
      this.roomId = this.roomId + 1;
      this.newRooms.push(newRoom);
      this.emit();
    },
    removeRoom: function (id) {
      for (let room of this.newRooms) {
        if (room.roomId === id) {
          this.newRooms.pop(room);
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
