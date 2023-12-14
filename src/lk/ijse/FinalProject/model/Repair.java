package lk.ijse.FinalProject.model;

public class Repair {
        private String repairid;
        private double repairPrize;
        private String vehicleId;

        public Repair() {

        }

        public Repair(String repairid, double repairPrize, String vehicleId) {
                this.repairid = repairid;
                this.repairPrize = repairPrize;
                this.vehicleId = vehicleId;
        }

        public String getRepairid() {
                return repairid;
        }

        public void setRepairid(String repairid) {
                this.repairid = repairid;
        }

        public double getRepairPrize() {
                return repairPrize;
        }

        public void setRepairPrize(double repairPrize) {
                this.repairPrize = repairPrize;
        }

        public String getVehicleId() {
                return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
                this.vehicleId = vehicleId;
        }

        @Override
        public String toString() {
                return "Repair{" +
                        "repairid='" + repairid + '\'' +
                        ", repairPrize=" + repairPrize +
                        ", vehicleId='" + vehicleId + '\'' +
                        '}';
        }
}
