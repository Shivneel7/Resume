#pragma once

#include "MovingTexRect.h"

/**Child of TexRect
 *  Used to display only portions of a .png file that is loaded in a TexRect
 * Includes animation capabilities
 * This is done as I did not want to modify the TexRect file given,
 * however my game needs added functionality which I had to add to a TexRect
 */
class Sprite : public MovingTexRect {
  protected:
    int rows;
    int cols;

    float xinc;
    float yinc;

    // where on the png file we get the current image
    float left;
    float right;
    float top;
    float bottom;

    bool done, loop;

  public:
    Sprite(const char *filename, float x, float y, float w, float h, ID);
    Sprite(const char *filename, float x, float y, float w, float h, float dx, float dy, ID);
    Sprite(const char *filename, int rows, int cols, float x, float y, float w, float h, bool l, ID);
    Sprite(const char *filename, int rows, int cols, float x, float y, float w, float h, float dx, float dy, bool l, ID);

    virtual void draw(float z = 0) const;

    bool isDone() const;

    void reset();

    virtual void advance();

    ~Sprite() {
    }
};
