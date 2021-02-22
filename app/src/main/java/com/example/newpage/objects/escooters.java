package com.example.newpage.objects;

public class escooters {

   private int escooter_id;
   private String escooter_model;
   private String escooter_name;
   private String escooter_number;
   private String escooter_model_year;

   public escooters() {
   }

   public escooters(int escooter_id, String escooter_model, String escooter_name, String escooter_number, String escooter_model_year) {
      this.escooter_id = escooter_id;
      this.escooter_model = escooter_model;
      this.escooter_name = escooter_name;
      this.escooter_number = escooter_number;
      this.escooter_model_year = escooter_model_year;
   }


   public int getEscooter_id() {
      return escooter_id;
   }

   public void setEscooter_id(int escooter_id) {
      this.escooter_id = escooter_id;
   }

   public String getEscooter_model() {
      return escooter_model;
   }

   public void setEscooter_model(String escooter_model) {
      this.escooter_model = escooter_model;
   }

   public String getEscooter_name() {
      return escooter_name;
   }

   public void setEscooter_name(String escooter_name) {
      this.escooter_name = escooter_name;
   }

   public String getEscooter_number() {
      return escooter_number;
   }

   public void setEscooter_number(String escooter_number) {
      this.escooter_number = escooter_number;
   }

   public String getEscooter_model_year() {
      return escooter_model_year;
   }

   public void setEscooter_model_year(String escooter_model_year) {
      this.escooter_model_year = escooter_model_year;
   }
}
