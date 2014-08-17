// All Examples Written by Casey Reas and Ben Fry

// unless otherwise stated.

// @pjs preload must be used to preload the image so that it will be available when used in the sketch  

/* @pjs preload="/assets/images/bg.png"; */
/* @pjs font="/assets/stylesheets/Lightman.ttf"; */



PImage bg;
int a; 
PFont metaBold;
Greeting obj;
void setup() 

{

  //size(1024,640);
 size(window.innerWidth, window.innerHeight); 
 frameRate(30);

  // The background image must be the same size as the parameters

  // into the size() method. In this program, the size of "milan_rubbish.jpg"

  // is 200 x 200 pixels.i
bg = loadImage("/assets/images/bg.png");
bg.resize(window.innerWidth, window.innerHeight);
PFont metaBold;

// The font "Meta-Bold.ttf" must be located in the 

// current sketch's "data" directory to load successfully

metaBold = createFont("Lightman",32);
textFont(metaBold, 44); 
digitalClock = new DigitalClock(40, (width-width/6), height-50);
obj = new Greeting("Hello World");
}

void setGreeting(String greeting){
obj.setGreeting(greeting);
}

void draw() 

{

  background(bg);

digitalClock.getTime();
digitalClock.display();
fill(255,255,255,125);
text(obj.greeting, width/2, height/2);
}


class Greeting{
String greeting;
Greeting(String greeting){
this.greeting = greeting;
}
void setGreeting(String greeting){
this.greeting = greeting;
}
}

class DigitalClock extends Clock {
  int fontSize;
  float x, y;
   
  DigitalClock(int _fontSize, float _x, float _y) {
    fontSize = _fontSize;
    x = _x;
    y = _y;
  }
   
  void getTime() {
    super.getTime();
  }
   
  void display() {
    textSize(fontSize);
    textAlign(CENTER);
    fill(255,255,255,125);
    text (h + ":" + nf(m, 2) + ":" + nf(s, 2), x, y);
  }
}
 
class Clock {
  int h, m, s;
  Clock() {
  }
   
  void getTime() {
    h = hour();
    m = minute();
    s = second();
  }
}

