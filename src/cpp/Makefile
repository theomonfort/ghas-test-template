# Compiles each vulnerable C++ sample to an object file so CodeQL can trace the build.
CXX      ?= g++
CXXFLAGS ?= -c -w -std=c++17
SRCS     := $(wildcard *.cpp)
OBJS     := $(SRCS:.cpp=.o)

all: $(OBJS)

%.o: %.cpp
	$(CXX) $(CXXFLAGS) $< -o $@

clean:
	rm -f *.o
