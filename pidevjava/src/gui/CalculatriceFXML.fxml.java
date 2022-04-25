<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.geometry.Insets?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.TextField?>
<?import javafx.scene.layout.AnchorPane?>
<?import javafx.scene.layout.HBox?>
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.text.Font?>

<AnchorPane id="AnchorPane" prefHeight="517.0" prefWidth="633.0" style="-fx-background-color: #FEEFE1;" xmlns="http://javafx.com/javafx/8.0.171" xmlns:fx="http://javafx.com/fxml/1" fx:controller="gui.CalculatriceController">
   <children>
      <VBox layoutY="26.0" prefHeight="470.0" prefWidth="633.0" spacing="2.0">
         <children>
            <TextField fx:id="Calculatrice" alignment="CENTER" prefHeight="31.0" prefWidth="583.0" style="-fx-background-color: #F1C9C9;">
               <font>
                  <Font name="Book Antiqua Bold Italic" size="30.0" />
               </font>
            </TextField>
            <HBox alignment="TOP_CENTER" prefHeight="100.0" prefWidth="200.0">
               <children>
                  <Button mnemonicParsing="false" onAction="#Number" prefHeight="65.0" prefWidth="70.0" style="-fx-background-color: #F1C9C9;" text="7">
                     <font>
                        <Font size="30.0" />
                     </font>
                     <padding>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </padding>
                     <HBox.margin>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </HBox.margin>
                  </Button>
                  <Button mnemonicParsing="false" onAction="#Number" prefHeight="65.0" prefWidth="70.0" style="-fx-background-color: #F1C9C9;" text="8">
                     <font>
                        <Font size="30.0" />
                     </font>
                     <HBox.margin>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </HBox.margin>
                     <padding>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </padding>
                  </Button>
                  <Button mnemonicParsing="false" onAction="#Number" prefHeight="65.0" prefWidth="71.0" style="-fx-background-color: #F1C9C9;" text="9">
                     <font>
                        <Font size="30.0" />
                     </font>
                     <HBox.margin>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </HBox.margin>
                     <padding>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </padding>
                  </Button>
                  <Button mnemonicParsing="false" onAction="#Operation" prefHeight="65.0" prefWidth="60.0" style="-fx-background-radius: 500; -fx-background-color: #E1EFFE;" styleClass="-" text="/">
                     <font>
                        <Font size="30.0" />
                     </font>
                  </Button>
               </children>
            </HBox>
            <HBox alignment="TOP_CENTER" prefHeight="100.0" prefWidth="200.0">
               <children>
                  <Button mnemonicParsing="false" onAction="#Number" prefHeight="65.0" prefWidth="73.0" style="-fx-background-color: #F1C9C9;" text="6">
                     <font>
                        <Font size="30.0" />
                     </font>
                     <HBox.margin>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </HBox.margin>
                     <padding>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </padding>
                  </Button>
                  <Button mnemonicParsing="false" onAction="#Number" prefHeight="65.0" prefWidth="73.0" style="-fx-background-color: #F1C9C9;" text="5">
                     <font>
                        <Font size="30.0" />
                     </font>
                     <HBox.margin>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </HBox.margin>
                     <padding>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </padding>
                  </Button>
                  <Button mnemonicParsing="false" onAction="#Number" prefHeight="65.0" prefWidth="72.0" style="-fx-background-color: #F1C9C9;" text="4">
                     <font>
                        <Font size="30.0" />
                     </font>
                     <HBox.margin>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </HBox.margin>
                     <padding>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </padding>
                  </Button>
                  <Button mnemonicParsing="false" onAction="#Operation" prefHeight="65.0" prefWidth="60.0" style="-fx-background-radius: 500; -fx-background-color: #E1EFFE;" text="-">
                     <font>
                        <Font size="30.0" />
                     </font>
                  </Button>
               </children>
            </HBox>
            <HBox alignment="TOP_CENTER" prefHeight="100.0" prefWidth="200.0">
               <children>
                  <Button mnemonicParsing="false" onAction="#Number" prefHeight="65.0" prefWidth="75.0" style="-fx-background-color: #F1C9C9;" text="3">
                     <font>
                        <Font size="30.0" />
                     </font>
                     <HBox.margin>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </HBox.margin>
                     <padding>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </padding>
                  </Button>
                  <Button mnemonicParsing="false" onAction="#Number" prefHeight="65.0" prefWidth="74.0" style="-fx-background-color: #F1C9C9;" text="2">
                     <font>
                        <Font size="30.0" />
                     </font>
                     <HBox.margin>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </HBox.margin>
                     <padding>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </padding>
                  </Button>
                  <Button mnemonicParsing="false" onAction="#Number" prefHeight="65.0" prefWidth="70.0" style="-fx-background-color: #F1C9C9;" text="1">
                     <font>
                        <Font size="30.0" />
                     </font>
                     <HBox.margin>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </HBox.margin>
                     <padding>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </padding>
                  </Button>
                  <Button mnemonicParsing="false" onAction="#Operation" prefHeight="65.0" prefWidth="61.0" style="-fx-background-radius: 500; -fx-background-color: #E1EFFE;" text="*">
                     <font>
                        <Font size="30.0" />
                     </font>
                  </Button>
               </children>
            </HBox>
            <HBox alignment="TOP_CENTER" prefHeight="100.0" prefWidth="486.0">
               <children>
                  <Button mnemonicParsing="false" onAction="#Number" prefHeight="65.0" prefWidth="130.0" style="-fx-background-color: #F1C9C9;" text="0">
                     <font>
                        <Font size="30.0" />
                     </font>
                     <HBox.margin>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </HBox.margin>
                     <padding>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </padding>
                  </Button>
                  <Button mnemonicParsing="false" onAction="#Operation" prefHeight="65.0" prefWidth="113.0" style="-fx-background-color: #F1C9C9;" text="=">
                     <font>
                        <Font size="30.0" />
                     </font>
                     <HBox.margin>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </HBox.margin>
                     <padding>
                        <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
                     </padding>
                  </Button>
                  <Button mnemonicParsing="false" onAction="#Operation" style="-fx-background-radius: 500; -fx-background-color: #E1EFFE;" text="+">
                     <font>
                        <Font size="30.0" />
                     </font>
                  </Button>
               </children>
            </HBox>
         </children>
      </VBox>
      <Button fx:id="btnlc" layoutY="481.0" mnemonicParsing="false" onAction="#RevenirLc" text="liste offres" />
      <Button layoutX="-124.0" layoutY="364.0" mnemonicParsing="false" onAction="#Number" prefHeight="65.0" prefWidth="70.0" style="-fx-background-color: #F1C9C9;" text="7">
         <font>
            <Font size="30.0" />
         </font>
         <padding>
            <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
         </padding>
      </Button>
   </children>
</AnchorPane>
