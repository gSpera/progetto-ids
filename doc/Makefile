OUT:=main.pdf
INPUT:=main.ty
ASSETS:=
CACHE:=.cache

$(OUT): $(INPUT).greu
	typst compile $(INPUT) $(OUT)

$(INPUT).greu: $(INPUT)
	cat $(INPUT) | greu -tmp-dir=$(CACHE) > $(INPUT).greu

.PHONY: watch
watch:
	entr -c /bin/sh -c 'make' <<< $(INPUT) $(ASSETS)

.PHONY: clean
clean:
	rm -rf $(CACHE) $(INPUT).greu

