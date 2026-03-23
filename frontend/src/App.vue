<template>
  <v-app>
    <v-container>
    <h1 class="mb-4">회의실 예약</h1>
     <v-row class="mb-4">
      <v-col cols="12" sm="4">
        <v-text-field
          v-model="selectedDate"
          label="조회 날짜"
          type="date"
        />
      </v-col>

      <v-col cols="12" sm="8" class="d-flex align-center">
        <v-btn color="primary" class="mr-2" @click="loadReservations">
          예약 목록 조회
        </v-btn>

        <v-btn color="success" @click="openReservationDialog">
          예약하기
        </v-btn>
      </v-col>
    </v-row>

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

    <!--예약 목록-->
    <v-simple-table>
      <thead>
        <tr>
          <th>예약 ID</th>
          <th>회의실 ID</th>
          <th>예약자 성명</th>
          <th>예약일</th>
          <th>예약 시작 시간</th>
          <th>예약 종료 시간</th>
          <th>비고</th>
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
          <td><v-btn color="error" small @click="deleteReservation(reservation.reservationId)">삭제</v-btn></td>
        </tr>
      </tbody>
    </v-simple-table>

    <!--예약 생성 모달-->
    <v-dialog v-model="dialog" max-width="600">
      <v-card>
        <v-card-title>
          <span class="text-h6">회의실 예약</span>
        </v-card-title>
        <v-card-text>
          <v-row>
            <v-col cols="12" sm="6">
              <v-select 
                v-model="newReservation.roomId"
                :items="rooms" 
                item-text="roomName" 
                item-value="roomId" 
                label="회의실 선택"
              />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field v-model="newReservation.reserverName" label="예약자명"
              />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field
                v-model="newReservation.reservationDate" label="예약 날짜" type="date"
              />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field
                v-model="newReservation.startTime"
                label="시작 시간"
                type="time"
              />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field
                v-model="newReservation.endTime"
                label="종료 시간"
                type="time"
              />
            </v-col>
          </v-row>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn text @click="closeReservationDialog">취소</v-btn>
          <v-btn color="success" @click="createReservation">저장</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
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
      selectedDate: new Date().toISOString().slice(0, 10),
      dialog: false,
      newReservation: {
        roomId: null,
        reservationName: "",
        reservationDate: new Date().toISOString().slice(0, 10),
        startTime: "",
        endTime:""
      }
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
    },

    openReservationDialog() {
      this.newReservation = {
        roomId: null,
        reserverName: "",
        reservationDate: this.selectedDate,
        startTime: "",
        endTime: ""
      };
      this.dialog = true;
    },

    closeReservationDialog() {
      this.dialog = false;
    },

    async createReservation() {
      try {
        //time은 "10:00" 형태이므로, 백엔드의 형태 "10:00:00" 이런식으로 맞추기 위함
          const payload = {
            ...this.newReservation,
            startTime: this.newReservation.startTime + ":00",
            endTime: this.newReservation.endTime + ":00"
          };
          await axios.post("http://localhost:8080/api/reservations", payload);
          alert("예약이 완료되었습니다.");
          this.dialog = false;
          this.loadReservations();
      }catch(error){
        console.error(error);

        if(error.response && error.response.data && error.response.data.error){
          alert(error.response.data.error);
        }else {
          alert("예약 생성 실패");
        }
      }
    },

    // 예약 삭제
    async deleteReservation(reservationId) {
      if(!confirm("정말 삭제하시겠습니까?")) return;

      try {
        await axios.delete(`http://localhost:8080/api/reservations/${reservationId}`);

        alert("삭제 완료");
        this.loadReservations();
      }catch(error){
        console.error(error);

        if(error.response && error.response.data && error.response.data.error){
          alert(error.response.data.error);
        }else{
          alert("예약 삭제 실패");
        }
      }
    }
  }
}

</script>
