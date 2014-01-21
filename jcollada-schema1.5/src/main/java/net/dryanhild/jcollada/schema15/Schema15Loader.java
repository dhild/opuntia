package net.dryanhild.jcollada.schema15;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.dryanhild.jcollada.VersionSupport;
import net.dryanhild.jcollada.data.ColladaScene;
import net.dryanhild.jcollada.schema15.generated.AssetType;
import net.dryanhild.jcollada.spi.ColladaLoaderService;
import net.dryanhild.jcollada.spi.ParsingContext;

import com.google.common.collect.ImmutableList;

public class Schema15Loader implements ColladaLoaderService {

    private static final VersionSupport VERSION = new VersionSupport(1, 5, 0, "1.5.0");

    private final Pattern versionPattern = Pattern.compile("COLLADA[^>]+version\\s?=\\s?\\\"1\\.5\\.0\\\"");

    @Override
    public Collection<VersionSupport> getColladaVersions() {
        return ImmutableList.of(VERSION);
    }

    @Override
    public boolean canLoad(CharSequence header) {
        Matcher matcher = versionPattern.matcher(header);
        return matcher.find();
    }

    @Override
    public ColladaScene load(ParsingContext context) {
        FileMarshaller marshaller = new FileMarshaller();

        marshaller.loadFrom(context.getMainFileReader(), context.isValidating());

        ColladaScene15 scene = new ColladaScene15();

        AssetType mainAsset = marshaller.getMainAsset();

        AssetHandler handler = new AssetHandler();

        scene.setMainAsset(handler.loadAssetDescription(mainAsset));

        return scene;
    }

}
