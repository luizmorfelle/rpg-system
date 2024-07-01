import { useEffect, useRef, useState } from "react";
import useWebSocket from "react-use-websocket";
import { RpgConfigs } from "./types";

export const WebSocketExample = () => {
  const { lastJsonMessage } = useWebSocket("ws://localhost:8080/ws");
  const [rpgConfigData, setRpgConfigData] = useState<RpgConfigs>();
  const audioRef = useRef<HTMLAudioElement>(null);

  useEffect(() => {
    if (lastJsonMessage) {
      setRpgConfigData(lastJsonMessage as RpgConfigs);
    }
  }, [lastJsonMessage]);

  console.log(rpgConfigData);
  return (
    rpgConfigData && (
      <div
        style={{
          width: "100vw",
          maxWidth: "100%",
          maxHeight: "100%",
          height: "100vh",
          backgroundImage: `url(${rpgConfigData?.scenario.image}.png)`,
          backgroundSize: "cover",
          backgroundRepeat: "no-repeat",
          backgroundPosition: "center",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          transition: "all 3s ease-in-out",
        }}
      >
        <audio
          src={`${rpgConfigData?.music.name}.mp3`}
          autoPlay
          loop
          controls
          ref={audioRef}
        />
        {rpgConfigData.enemies.map((enemy) => (
          <div
            key={enemy.id}
            style={{
              // position: "absolute",
              // top: 200,
              // left: 50,
              height: "20rem",
            }}
          >
            <img
              src={`${enemy.image}.png`}
              alt=""
              style={{
                height: "130%",
                objectFit: "cover",
                // position: "relative",
                // top: 0,
                // left: 0,
              }}
            />
          </div>
        ))}
      </div>
    )
  );
};

function Item() {
  return (
    <div
      style={{
        position: "absolute",
        top: 400,
        left: 50,
        height: "20rem",
      }}
    >
      <img
        src="public/orc.png"
        alt=""
        style={{
          height: "130%",
          objectFit: "cover",
          position: "relative",
          top: 0,
          left: 0,
        }}
      />
    </div>
  );
}
