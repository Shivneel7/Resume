#pragma once

#include "TexRect.h"

/** ID of the movingSprite object for object interaction purposes in the Game.cpp file
 */
enum ID { fruit, bomb, player, spiny, health, energy, doubleSpiny, demo, explosion, defaultID };

/**This is a base class for any object in the game that moves.
 *
 * Child of TexRect Class:
 *    Adds the idle function as well as member varaibles dx and dy, for movement purposes
 *
 *Contains an enum called ID so that the game can tell apart different objects
 *
 *
 */
class MovingTexRect : public TexRect {
  protected:
    float dx, dy;
    ID id;

  public:
    MovingTexRect(const char *filename, float x, float y, float w, float h, float dx, float dy, ID id);

    virtual void idle();

    // Call showBounds() in the draw method of a child to draw the collision bound rectangle for debugging purposes
    virtual void showBounds() const;

    void setDX(float dx);
    float getDX() const;

    void setDY(float dy);
    float getDY() const;

    ID getID();
};