cmake_minimum_required(VERSION 3.4.1)

add_library(TextBookGL SHARED TextBookGL.cpp)
target_link_libraries(TextBookGL android log EGL GLESv3 jnigraphics)