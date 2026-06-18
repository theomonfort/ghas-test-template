# Compiles each vulnerable C sample to an object file so CodeQL can trace the build.
CC      ?= gcc
CFLAGS  ?= -c -w
SRCS    := $(wildcard *.c)
OBJS    := $(SRCS:.c=.o)

all: $(OBJS)

%.o: %.c
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm -f *.o
