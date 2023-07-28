local function loadHangMan(wordString)
    lives = 6
    letters = {}
	foundLetters = {}
	answerHistory = {}
    for i = 1, string.len(wordString) do
		local character = string.lower(string.sub(wordString, i, i))
		if character ~= " " then
			table.insert(letters, character)
		end
		foundLetters[i] = nil
    end
end

function love.load()
    loadHangMan("mamifero")
end

function love.draw()
    love.graphics.print("Lives - " .. lives, 0, 0)
    for i = 1, #letters do
        love.graphics.print(foundLetters[i] or "_", (i - 1) * 20, 20)  
    end
end

function getTableSize(t)
    local count = 0
    for i, v in ipairs(t) do
		count = count + 1
    end
    return count
end

function love.keypressed(key)
	local guessChar = string.lower(key)
    if getTableSize(foundLetters) < getTableSize(letters) and lives > 0 then
        local cond = false
		local repeatCond = false
        for i = 1, #letters do
			for j = 1, #answerHistory do
				if guessChar == answerHistory[i] then
					repeatCond = true
					break
				end
			end
			if not repeatCond then
				if guessChar == letters[i] and guessChar ~= foundLetters[i] then
					foundLetters[i] = guessChar
					cond = true
				elseif guessChar == foundLetters[i] then
					repeatCond = true
					break
				end
			else
				break
			end
        end
		if repeatCond then
			print("Ya lo dijistes!")
		else
			lives = lives - (cond and 0 or 1)
			local goodStuff, badStuff = getTableSize(foundLetters) >= getTableSize(letters) and "¡Has Ganado!" or "¡Correcto!", lives <= 0 and "Has Perdido..." or "Incorrecto..."
			print(cond and goodStuff or badStuff)
			if not cond then
				table.insert(answerHistory, guessChar)
			end
		end
    end
end