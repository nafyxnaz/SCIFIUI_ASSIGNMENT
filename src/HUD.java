import java.awt.Font;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nafy
 */

public class HUD extends GameObject{
  
    Time t;
    StarMap m;
    Radar r;
    UI ui;

    int borderW;
    int borderH;
    int buttonAmount = 4;
    int buttonInterval = borderH / 3;
    int barAmount = 4;
    int j;
    int buttonWidth;
    int buttonHeight;
    int i; 



    ArrayList <Button> buttons = new ArrayList <>();
    ArrayList <Bar> bars = new ArrayList<>();

    HUD(UI ui){
        this.ui = ui;
        this.m = new StarMap( ui, ui.width / 32, ui.height /32);
        
        this.r = new Radar( ui, 100, (ui.width / 4) / 2 ,  -(ui.height / 4 ) / 2 , 0.5f);
        borderW = ui.width / 4;
        borderH = ui.height / 4;
        buttonWidth = ui.width/25;
        buttonHeight = ui.height/25;
        //loop to add 4 buttons
        for(i = 0; i < buttonAmount; i++){
            buttons.add(new Button(ui, (borderW/5)*(i+1)-(buttonWidth/2), ui.height - (borderH/2)-(buttonHeight/2), buttonWidth, buttonHeight, ""));
        }

       // loop to add in the bar meters
        for( i = 0; i < barAmount; i ++){ 
            bars.add(new Bar(ui, ui.width - ((borderW/5)*(i+1)-(buttonWidth/2)+ buttonWidth), ui.height - (borderH * 1.25f), buttonWidth));
        }

        //creates 
        this.t = new Time(ui,0,borderH + (borderH / 16));

    }
    
    @Override
    public void render(){
            ui.background(0);
            ui.fill(0);
      
            
        //Covers the top white.
            ui.rect(0,0, ui.width , borderH);

        //creates the top border and bottom border layout
            ui.fill(52, 203, 203);
            ui.stroke(0);
            ui.pushMatrix();
            ui.translate(borderW , 0);
            ui.rect(0 , 0, ui.width / 2 , borderH / 2);
            ui.translate(0 , ui.height - borderW / 2);
            ui.fill(52, 203, 203);
            ui.rect(0, 0 , ui.width / 2 , borderH);
            ui.fill(255, 0, 0);
            ui.rect(0 , 0, ui.width / 2 , borderH / 4);
            ui.stroke(0);
            ui.popMatrix();

        //creates the left side layout   
            ui.fill(26, 101, 101);
            ui.rect(0 , borderH , borderW , ui.height /2); 
            ui.triangle(0 , borderH / 2 , 0 , borderH , borderW , borderH);
            ui.triangle(0 , 0 , borderW , 0 , borderW , borderH / 2);
            ui.quad(0 , (ui.height - borderH ) + (borderH / 4) , borderW , (ui.height - borderH ) + (borderH /4) , borderW + (borderW / 4) , ui.height, 0 , ui.height); 
            ui.fill(0);
            ui.stroke(0);
            ui.rect(0, ui.height - borderH, borderW + (borderW / 12) ,borderH / 4);
            ui.quad(borderW + (borderW / 12) , ui.height - borderH , borderW, ui.height - (borderH - borderH / 4) , borderW + (borderW / 4) , ui.height , (borderW + (borderW / 4)) + borderW / 8 , ui.height); 
            ui.fill(255);
            ui.stroke(0);
            
        ui.textSize(25);
        ui.fill(179, 143, 0);
        ui.text("System ", ui.width/8, ui.height/3);
      
        
        
        ui.textSize(25);
        ui.fill(204, 163, 0);
        ui.text("Diagnostics", ui.width/8, ui.height/2.5f);

        //creates the right side layout
            ui.pushMatrix();
            ui.translate(ui.width - borderW , 0);
            ui.fill(174, 234, 234);
            ui.rect(0 , borderH , borderW , ui.height /2);
            ui.quad(0 , (ui.height - borderH ) + (borderH / 4) , borderW , (ui.height - borderH ) + (borderH /4) ,  borderW  , ui.height, -(borderW / 4) , ui.height); 
            ui.triangle(borderW , borderH / 2 , 0 , borderH , borderW , borderH); 
            ui.triangle(0 , 0 , borderW , 0 , 0 , borderH / 2);
            ui.fill(0);
            ui.stroke(0);
            ui.rect(-(borderW/12), ui.height - borderH, borderW + (borderW /12) ,borderH / 4);
            ui.quad(-(borderW / 12) , ui.height - borderH , 0, ui.height - (borderH - borderH / 4) ,  - (borderW /4 ) , ui.height ,  - (borderW / 4) - borderW / 8 , ui.height); 
            ui.fill(0);
            ui.popMatrix();

            
            
            
            r.render();
            r.update();

            //map
                if ( ui.click == true  ){
                        ui.pushMatrix();
                        ui.translate(borderW,borderH);
                        m.render();
                        ui.popMatrix();
     }

            //time
                        ui.pushMatrix();
                        ui.translate(ui.width / 2, ui.height / 2);
                        t.render();
                        ui.popMatrix();


                        ui.pushMatrix();
                        ui.translate(ui.width - borderW , ui.height / 2);
                        ui.popMatrix();

        ui.strokeWeight(5);
        
        //update/render
        buttons.stream().map((b) -> {
            b.update();
            return b;
        }).forEachOrdered((b) -> {
            b.render();
        });
        
        
        
        bars.stream().map((b) -> {
            b.update();
            return b;
        }).forEachOrdered((b) -> {
            b.render();
        });
        
        
        
       
   }
 }