<template>
  <v-app>
    <v-container>
    <!--회의실 목록-->
      <h1>회의실 목록</h1>
      <v-simple-table class="mt-4">
        <thead>
          <tr>
            <th>회의실 ID</th>
            <th>회의실 이름</th>
            <th>수용 인원</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="room in rooms" :key="room.roomId">
            <td>{{room.roomId}}</td>
            <td>{{room.roomName}}</td>
            <td>{{room.capacity}}</td>
          </tr>
        </tbody>
      </v-simple-table>

    <!--날짜별 예약 조회-->
    <h2 class="mb-2">날짜별 예약 목록 조회</h2>
    <v-row class="mb-4">
      <v-col cols="12" sm="4">
        <v-text-field v-model="selectedDate" label="조회 날짜" type="date"/>
      </v-col>

      <v-col cols="12" sm="4" class="d-flex align-center">
        <v-btn color="primary" @click="loadReservations">
          예약 목록 조회
        </v-btn>
      </v-col>
    </v-row>
    <v-simple-table>
      <thead>
        <tr>
          <th>예약 ID</th>
          <th>회의실 ID</th>
          <th>예약자 성명</th>
          <th>예약일</th>
          <th>예약 시작 시간</th>
          <th>예약 종료 시간</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="reservation in reservations" :key="reservation.reservationId">
          <td>{{reservation.reservationId}}</td>
          <td>{{reservation.roomId}}</td>
          <td>{{reservation.reserverName}}</td>
          <td>{{reservation.reservationDate}}</td>
          <td>{{reservation.startTime}}</td>
          <td>{{reservation.endTime}}</td>
        </tr>
      </tbody>

    </v-simple-table>
    </v-container>
  </v-app>
</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      rooms: [],
      reservations: [],
      selectedDate: new Date().toISOString().slice(0, 10)
    };
  },
  mounted() {
    this.loadRooms(); // 자동 호출
    this.loadReservations();
  },
  methods: {
    async loadRooms() {
      try {
        const response = await axios.get("http://localhost:8080/api/rooms");
        this.rooms = response.data;
      }catch (error){
        console.error(error);
      }
    },
    async loadReservations() {
      try {
        const response = await axios.get("http://localhost:8080/api/reservations", {params:{
            date: this.selectedDate
          }
        });
        this.reservations = response.data;
      }catch(error){
        console.error(error);
        alert("예약 목록 조회 실패");
      }
    }
  }
}

</script>
